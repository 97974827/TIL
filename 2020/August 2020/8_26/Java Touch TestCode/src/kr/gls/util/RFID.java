package kr.gls.util;
import javafx.collections.ObservableList;
import kr.gls.Main;
import kr.gls.model.Config;
import kr.gls.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;

public class RFID {
    private PcscReader pcscReader;
    private Acr1281 acr1281;
    private MifareClassic mifareClassic;
    private Acr1281.CHIP_TYPE currentChipType = Acr1281.CHIP_TYPE.UNKNOWN;
    CommonUtil cu = new CommonUtil();

    private Map<String, String> config = cu.getConfig();
    private Map<Long, String> crc_data = cu.getCrc();
    private String shop_no = config.get("shop_id");
    private String manager_id = config.get("manager_id");
    private String manager_encrypt = config.get("encrypt");

    public int remain_money = 0;
    public int total_charge_money = 0;
    public boolean charge_state = false;
    public boolean rf_connected = false;

    public static boolean thread_stop = false;

    public RFID() {
        handleReader();
    }

    public void handleReader() {
        thread_stop = false;
        Thread thread = new Thread() {
            @Override
            public void run() {
                while(!thread_stop) {
                    if (Main.RF_FLAG == 1) {
                        readCard();
                    } else {
                        chargeCard();
                    }

                    try{Thread.sleep(1000);} catch (Exception e){}
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    public void chargeCard() {
        pcscReader = new PcscReader(); //instantiate an event handler object
        pcscReader.setEventHandler(new ReaderEvents());
        acr1281 = new Acr1281(pcscReader);
        ReaderUtil ru = new ReaderUtil();
        String[] readerList = null;

        try {
            readerList = acr1281.getPcscConnection().listTerminals();

            if (readerList.length == 0) {
                System.out.println("리더기 연결 안됨");
                rf_connected = false;
                return;
            } else {
                byte[] buff 	  = new byte[16]; 		   //카드에 보내는 변수
                rf_connected = true;
                String rdrcon = (String)readerList[0];
                String serial_number = "";

                acr1281.getPcscConnection().connect(rdrcon, "*");

                currentChipType = acr1281.getChipType();

                mifareClassic = new MifareClassic(acr1281.getPcscConnection());

                serial_number = mifareClassic.readSerialNumber();

                byte[] key = new byte[6];
                byte keyNumber;
                keyNumber = (byte)((Integer)Integer.parseInt("1", 16)).byteValue();

                key[0] = (byte)((Integer)Integer.parseInt(manager_id.substring(0, 2), 16)).byteValue();
                key[1] = (byte)((Integer)Integer.parseInt(manager_id.substring(2, 4), 16)).byteValue();
                key[2] = (byte)((Integer)Integer.parseInt(manager_id.substring(4, 6), 16)).byteValue();
                key[3] = (byte)((Integer)Integer.parseInt(manager_id.substring(6, 8), 16)).byteValue();
                key[4] = (byte)((Integer)Integer.parseInt(manager_id.substring(8, 10), 16)).byteValue();
                key[5] = (byte)((Integer)Integer.parseInt(manager_id.substring(10, 12), 16)).byteValue();

                if(!acr1281.loadAuthKey(keyNumber, key))
                    System.out.println("키 로드 실패");

                byte blockNumber;
                String keyAddr = "";
                keyNumber = 0x20;
                Acr1281.KEYTYPES keyType = Acr1281.KEYTYPES.ACR1281_KEYTYPE_A;

                keyNumber = (byte)((Integer)Integer.parseInt("01", 16)).byteValue();
                if(keyNumber > (byte) 0x01)
                {
                    keyAddr = "01";
                    return;
                }

                blockNumber = Byte.parseByte("1");

                if(!acr1281.authenticate(blockNumber, keyType, keyNumber)) {
                    if(acr1281.getPcscConnection().isConnectionActive())
                        acr1281.getPcscConnection().disconnect();
                    ru.buzzer_handle(5);
                    ru.buzzer_handle(5);
                    return;
                }

                int blockNumber1;
                int length;
                int res_mny = 0;
                byte[] tempStr;
                String temp_mny = "";
                String temp_mny1 = "";
                String temp_mny2 = "";
                String temp_mny3 = "";
                String result_mny = "";
                String key1 = "";
                String key2 = "";

                String shop_id = "";
                String shop_id1 = "";
                String shop_id2 = "";

                blockNumber1 = 1;
                length = 16;

                tempStr = mifareClassic.readBinaryBlock((byte)blockNumber1, (byte)length);

                System.out.println("===================");
                System.out.print("바이너리 블럭 :::");
                for (int i = 0; i<tempStr.length; i++) {
                    System.out.print(tempStr[i]);
                    if (i == 0) {
                        temp_mny = String.format("%02X", tempStr[i]);
                        if (temp_mny.equals("00")) {
                            temp_mny = "";
                        }
                    }
                    if (i == 1) {
                        temp_mny1 = String.format("%02X", tempStr[i]);
                        if (temp_mny1.equals("00")) {
                            temp_mny1 = "";
                        }
                    }

                    if (i == 2) {
                        temp_mny2 = String.format("%02X", tempStr[i]);
                        if (temp_mny2.equals("00")) {
                            temp_mny2 = "";
                        }
                    }

                    if (i == 3) {
                        temp_mny3 = String.format("%02X", tempStr[i]);
                        if (temp_mny3.equals("00")) {
                            temp_mny3= "";
                        }
                    }

                    if (i == 4) {
                        key1 = String.format("%02X", tempStr[i]);
                    }

                    if (i == 13) {
                        key2 = String.format("%02X", tempStr[i]);
                    }

                    if (i == 14) {
                        shop_id1 = String.format("%02X", tempStr[i]);
                    }

                    if (i == 15) {
                        shop_id2 = String.format("%02X", tempStr[i]);
                    }

                }
                System.out.print("\n=====================");
                result_mny = temp_mny3 + temp_mny2 + temp_mny + temp_mny1;

                shop_id = shop_id1 + shop_id2;

                if (result_mny.equals("")) {
                    res_mny = 0;
                } else {
                    res_mny = Integer.valueOf(result_mny, 16);
                }

                int compare_shop_id = Integer.valueOf(shop_id, 16);

                if (Integer.parseInt(shop_no) != compare_shop_id) {
                    if(acr1281.getPcscConnection().isConnectionActive())
                        acr1281.getPcscConnection().disconnect();
                    ru.buzzer_handle(1);
                    ru.buzzer_handle(1);
                }

                if (manager_encrypt.equals("1")) {
                    if(!card_encrypt(serial_number, res_mny, shop_id, key1, key2)) {
                        if(acr1281.getPcscConnection().isConnectionActive())
                            acr1281.getPcscConnection().disconnect();
                        ru.buzzer_handle(5);
                        ru.buzzer_handle(5);
                        return;
                    }
                }


                if (Main.bill.total_bill_money > 0) {
//                    res_mny =  Math.abs(Integer.parseInt(charge_field.getText())) + res_mny +  Math.abs(bonus) - Math.abs(minus);
                    res_mny =  Main.bill.total_bill_money + res_mny;
                    String suff = String.format("%08X", res_mny);

                    if (manager_encrypt.equals("1")) {
                        buff = encryptCharge(suff, serial_number, shop_id);
                    } else {
                        buff = commonCharge(suff, shop_id1, shop_id2);
                    }

                    if(mifareClassic.updateBinaryBlock((byte)blockNumber, buff, (byte)length)) {
                        if (acr1281.getPcscConnection().isConnectionActive())
                            acr1281.getPcscConnection().disconnect();
                        total_charge_money = res_mny;
                        charge_state = true;
                        ru.buzzer_handle(10);
                    }
                }

            }

        } catch (Exception e) {

        }
    }

    public void readCard() {
        pcscReader = new PcscReader(); //instantiate an event handler object
        pcscReader.setEventHandler(new ReaderEvents());
        acr1281 = new Acr1281(pcscReader);
        ReaderUtil ru = new ReaderUtil();
        String[] readerList = null;

        try {
            readerList = acr1281.getPcscConnection().listTerminals();

            if (readerList.length == 0) {
                System.out.println("리더기 연결 안됨");
                rf_connected = false;
                return;
            } else {
                rf_connected = true;
                String rdrcon = (String)readerList[0];
                String serial_number = "";

                acr1281.getPcscConnection().connect(rdrcon, "*");

                currentChipType = acr1281.getChipType();

                mifareClassic = new MifareClassic(acr1281.getPcscConnection());

                serial_number = mifareClassic.readSerialNumber();

                byte[] key = new byte[6];
                byte keyNumber;
                keyNumber = (byte)((Integer)Integer.parseInt("1", 16)).byteValue();

                key[0] = (byte)((Integer)Integer.parseInt(manager_id.substring(0, 2), 16)).byteValue();
                key[1] = (byte)((Integer)Integer.parseInt(manager_id.substring(2, 4), 16)).byteValue();
                key[2] = (byte)((Integer)Integer.parseInt(manager_id.substring(4, 6), 16)).byteValue();
                key[3] = (byte)((Integer)Integer.parseInt(manager_id.substring(6, 8), 16)).byteValue();
                key[4] = (byte)((Integer)Integer.parseInt(manager_id.substring(8, 10), 16)).byteValue();
                key[5] = (byte)((Integer)Integer.parseInt(manager_id.substring(10, 12), 16)).byteValue();

                if(!acr1281.loadAuthKey(keyNumber, key))
                    System.out.println("키 로드 실패");

                byte blockNumber;
                String keyAddr = "";
                keyNumber = 0x20;
                Acr1281.KEYTYPES keyType = Acr1281.KEYTYPES.ACR1281_KEYTYPE_A;

                keyNumber = (byte)((Integer)Integer.parseInt("01", 16)).byteValue();
                if(keyNumber > (byte) 0x01)
                {
                    keyAddr = "01";
                    return;
                }

                blockNumber = Byte.parseByte("1");

                if(!acr1281.authenticate(blockNumber, keyType, keyNumber)) {
                    if(acr1281.getPcscConnection().isConnectionActive())
                        acr1281.getPcscConnection().disconnect();
                    ru.buzzer_handle(5);
                    ru.buzzer_handle(5);
                    return;
                }

                int blockNumber1;
                int length;
                int res_mny = 0;
                byte[] tempStr;
                String temp_mny = "";
                String temp_mny1 = "";
                String temp_mny2 = "";
                String temp_mny3 = "";
                String result_mny = "";
                String key1 = "";
                String key2 = "";

                String shop_id = "";
                String shop_id1 = "";
                String shop_id2 = "";

                blockNumber1 = 1;
                length = 16;

                tempStr = mifareClassic.readBinaryBlock((byte)blockNumber1, (byte)length);

                for (int i = 0; i<tempStr.length; i++) {
                    if (i == 0) {
                        temp_mny = String.format("%02X", tempStr[i]);
                        if (temp_mny.equals("00")) {
                            temp_mny = "";
                        }
                    }
                    if (i == 1) {
                        temp_mny1 = String.format("%02X", tempStr[i]);
                        if (temp_mny1.equals("00")) {
                            temp_mny1 = "";
                        }
                    }

                    if (i == 2) {
                        temp_mny2 = String.format("%02X", tempStr[i]);
                        if (temp_mny2.equals("00")) {
                            temp_mny2 = "";
                        }
                    }

                    if (i == 3) {
                        temp_mny3 = String.format("%02X", tempStr[i]);
                        if (temp_mny3.equals("00")) {
                            temp_mny3= "";
                        }
                    }

                    if (i == 4) {
                        key1 = String.format("%02X", tempStr[i]);
                    }

                    if (i == 13) {
                        key2 = String.format("%02X", tempStr[i]);
                    }

                    if (i == 14) {
                        shop_id1 = String.format("%02X", tempStr[i]);
                    }

                    if (i == 15) {
                        shop_id2 = String.format("%02X", tempStr[i]);
                    }

                }

                result_mny = temp_mny3 + temp_mny2 + temp_mny + temp_mny1;

                shop_id = shop_id1 + shop_id2;

                if (result_mny.equals("")) {
                    res_mny = 0;
                } else {
                    res_mny = Integer.valueOf(result_mny, 16);
                }

                int compare_shop_id = Integer.valueOf(shop_id, 16);

                if (Integer.parseInt(shop_no) != compare_shop_id) {
                    if(acr1281.getPcscConnection().isConnectionActive())
                        acr1281.getPcscConnection().disconnect();
                    ru.buzzer_handle(1);
                    ru.buzzer_handle(1);
                }

                if (manager_encrypt.equals("1")) {
                    if(!card_encrypt(serial_number, res_mny, shop_id, key1, key2)) {
                        if(acr1281.getPcscConnection().isConnectionActive())
                            acr1281.getPcscConnection().disconnect();
                        ru.buzzer_handle(5);
                        ru.buzzer_handle(5);
                        return;
                    }
                }

                if(acr1281.getPcscConnection().isConnectionActive())
                    acr1281.getPcscConnection().disconnect();
                remain_money = res_mny;
                Main.test_count += 1;
                System.out.println("잔액조회 회수 " + Main.test_count + "회");
                System.out.println("잔액 ::::" + res_mny);
                ru.buzzer_handle(10);
            }

        } catch (Exception e) {

        }
    }

    public boolean card_encrypt(String serial_number, int remain_money, String shop_id, String key1, String key2) {
        boolean res = false;
        int compare_result = 0;
        Long compare_money = (long) 0;
        Long compare_money_index = (long) 0;

        Long shop_total = (long) 0;
        Long compare_shop_id_index = (long) 0;

        String compare_money_crc = "";
        String compare_shop_id_crc = "";

        compare_result = remain_money / 1000;

        compare_money = compare_result + Long.parseLong(serial_number, 16);
        compare_money_index = (compare_money % 100) + 1;

        compare_money_crc = crc_data.get(compare_money_index);

        shop_total = Long.parseLong(serial_number, 16) + Integer.valueOf(shop_id, 16);

        compare_shop_id_index = (shop_total % 100) + 1;

        compare_shop_id_crc = crc_data.get(compare_shop_id_index);

        int test1 = Integer.valueOf(key1, 16);
        int test2 = Integer.valueOf(compare_money_crc, 16);

        int test3 = Integer.valueOf(key2, 16);
        int test4 = Integer.valueOf(compare_shop_id_crc, 16);

        if ((test1 == test2) && (test3 == test4)) {
            res = true;
        }

        return res;
    }

    public byte get_xor(String s) {
        byte c;
        byte a = (byte)((Integer)Integer.parseInt(s, 16)).byteValue();
        byte b = (byte) 0xFF;

        c = (byte) (a ^ b);

        return c;
    }

    public byte[] commonCharge(String suff, String shop_id1, String shop_id2) {
        byte[] buff = new byte[16];

        buff[0] = (byte)((Integer)Integer.parseInt(suff.substring(4, 6), 16)).byteValue();
        buff[1] = (byte)((Integer)Integer.parseInt(suff.substring(6, 8), 16)).byteValue();
        buff[2] = (byte)((Integer)Integer.parseInt(suff.substring(2, 4), 16)).byteValue();
        buff[3] = (byte)((Integer)Integer.parseInt(suff.substring(0, 2), 16)).byteValue();
        buff[4] = this.get_xor(suff.substring(4, 6));
        buff[5] = this.get_xor(suff.substring(6, 8));
        buff[6] = this.get_xor(suff.substring(2, 4));
        buff[7] = this.get_xor(suff.substring(0, 2));
        buff[8] = (byte) 0xAA;
        buff[9] = (byte) 0x55;
        buff[10] = (byte) 0x00;
        buff[11] = (byte) 0x00;
        buff[12] = this.get_xor(shop_id1);
        buff[13] = this.get_xor(shop_id2);
        buff[14] = (byte)((Integer)Integer.parseInt(shop_id1, 16)).byteValue();
        buff[15] = (byte)((Integer)Integer.parseInt(shop_id2, 16)).byteValue();

        return buff;
    }

    public byte[] encryptCharge(String suff, String serial_number, String shop_id) {
        Long compare_result = Long.parseLong(suff, 16) / 1000;
        Long compare_money = compare_result + Long.parseLong(serial_number, 16);
        Long crc_index = (compare_money % 100) + 1;

        Long shop_total = Long.parseLong(serial_number, 16) + Integer.valueOf(shop_id, 16);
        Long shop_crc_index = (shop_total % 100) + 1;

        byte[] buff = new byte[16];

        buff[0] = (byte)((Integer)Integer.parseInt(suff.substring(4, 6), 16)).byteValue();
        buff[1] = (byte)((Integer)Integer.parseInt(suff.substring(6, 8), 16)).byteValue();
        buff[2] = (byte)((Integer)Integer.parseInt(suff.substring(2, 4), 16)).byteValue();
        buff[3] = (byte)((Integer)Integer.parseInt(suff.substring(0, 2), 16)).byteValue();
        buff[4] = (byte)((Integer)Integer.parseInt(crc_data.get(crc_index), 16)).byteValue();
        buff[5] = (byte) 0x00;
        buff[6] = (byte) 0x00;
        buff[7] = (byte) 0x00;
        buff[8] = (byte) 0xAA;
        buff[9] = (byte) 0x55;
        buff[10] = (byte) 0x00;
        buff[11] = (byte) 0x00;
        buff[12] = (byte) 0x00;
        buff[13] = (byte)((Integer)Integer.parseInt(crc_data.get(shop_crc_index), 16)).byteValue();
        buff[14] = (byte)((Integer)Integer.parseInt(shop_id.substring(0, 2), 16)).byteValue();
        buff[15] = (byte)((Integer)Integer.parseInt(shop_id.substring(2, 4), 16)).byteValue();

        return buff;
    }

}
