package kr.gls.util;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import jssc.SerialPort;
import jssc.SerialPortList;
import kr.gls.Main;

import java.io.File;
import java.util.ArrayList;

public class Bill {
    SerialPort serialPort;
    public boolean bill_connected = false;
    public boolean stop;
    int ACTIVE_STATE;
    int BILL_DATA = 0;
    int bill_money = 0;
    public int total_bill_money = 0;

    public Bill () {
        try {
            serialPort = new SerialPort("/dev/USB01");
            if (!serialPort.isOpened()) {
                serialPort.openPort();//Open serial port
                serialPort.setParams(SerialPort.BAUDRATE_9600,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                readBlock();
            }
            activeStateThread();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void readBlock() {

        Thread thread = new Thread() {
            boolean connect = false;
            @Override
            public void run() {
                byte[] read = {};
                while(true) {
                    try {
                        read = serialPort.readBytes();
                        ArrayList<Integer> arrays = new ArrayList<>();
                        if (read != null && read.length >= 5) {
                            for(int i=0; i<5; i++) {
                                arrays.add((int)read[i]);
                            }
                            // $me! 일 때
                            if ((arrays.get(0) == 36) && (arrays.get(1) == 109) && (arrays.get(2) == 101)) {
                                bill_connected = true;
                            } else {
                                if ((arrays.get(0) == 36) && (arrays.get(1) == 103) && (arrays.get(2) == 97)){
                                    // active status
                                    ACTIVE_STATE = arrays.get(3);
                                    // 권종 읽기
                                } else if ((arrays.get(0) == 36) && (arrays.get(1) == 0x67) && (arrays.get(2) == 0x62)) {
                                    BILL_DATA = arrays.get(3);
                                    switch (BILL_DATA) {
                                        case 1:
                                            bill_money = 1000;
                                            break;

                                        case 5:
                                            bill_money = 5000;
                                            break;

                                        case 10:
                                            bill_money = 10000;
                                            break;

                                        case 50:
                                            bill_money = 50000;
                                            break;

                                        default:
                                            bill_money = 0;
                                        break;
                                    }

                                    if (bill_money > 0) {
                                        try {
                                            Main.mp.stop();
                                        } catch (NullPointerException e) {

                                        }

                                        String path = new File("resources/msgs/msg022.wav").getAbsolutePath();
                                        Main.me = new Media(new File(path).toURI().toString());
                                        Main.mp = new MediaPlayer(Main.me);
                                        Main.mp.play();
                                    }
                                    total_bill_money += bill_money;
                                }
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        bill_connected = false;
                    }

                    try{Thread.sleep(500);} catch (Exception e){}
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
    }

    public void writeBlock(String command) {
        byte[] buff = new byte[5];
        switch (command) {
            case "hi":
                buff[0] = (byte) 0x24;
                buff[1] = (byte) 0x48;
                buff[2] = (byte) 0x69;
                buff[3] = (byte) 0x3f;
                buff[4] = (byte) 0xf0;
                break;

            case "enable":
                buff[0] = (byte) 0x24;
                buff[1] = (byte) 0x53;
                buff[2] = (byte) 0x41;
                buff[3] = (byte) 0x0D;
                buff[4] = (byte) 0xA1;

                /*
                 * 응답코드 : 0x24, 0x4F, 0x4B, 0x61, 0xFB
                 * */
                break;

            case "disable":
                buff[0] = (byte) 0x24;
                buff[1] = (byte) 0x53;
                buff[2] = (byte) 0x41;
                buff[3] = (byte) 0x0E;
                buff[4] = (byte) 0xA2;
            break;

            case "bill_data":
                buff[0] = (byte) 0x24;
                buff[1] = (byte) 0x47;
                buff[2] = (byte) 0x42;
                buff[3] = (byte) 0x3f;
                buff[4] = (byte) 0xc8;
                break;

            case "getActiveStatus":
                buff[0] = (byte) 0x24;
                buff[1] = (byte) 0x47;
                buff[2] = (byte) 0x41;
                buff[3] = (byte) 0x3f;
                buff[4] = (byte) 0xc7;
                break;
        }
        try {
            serialPort.writeBytes(buff);
            try { Thread.sleep(1000);} catch (Exception e) {}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setConnected(boolean connected) {
        this.bill_connected = connected;
    }

    public boolean getConneted() {
        return this.bill_connected;
    }

    public void activeStateThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                int i = 1;
                while(!stop) {
                    writeBlock("getActiveStatus");
                    switch (ACTIVE_STATE) {
                        case 11:
                            writeBlock("bill_data");
                            writeBlock("enable");
                            break;
                    }
                    i++;
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
    }

}