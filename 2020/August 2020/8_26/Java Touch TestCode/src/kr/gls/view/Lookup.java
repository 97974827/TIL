package kr.gls.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import kr.gls.Main;

import java.io.File;

import static kr.gls.util.RFID.thread_stop;

public class Lookup {
    @FXML
    private ImageView back_btn;

    @FXML
    private Label lookup_money_label;

    public Main mainApp;

    boolean stop;

    boolean sound_stop;

    @FXML
    private void initialize() {
        setLookupMoneyLabel();
        if (Main.rfid.remain_money <= 0) {
            handleSound();
        }

        if (Main.bill.stop) {
            Main.bill.stop = false;
            Main.bill.activeStateThread();
        }
    }

    public void setLookupMoneyLabel() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while(true) {
                    if (!stop) {
                        if (Main.rfid.remain_money > 0) {
                            thread_stop = true;
                        }
                        Platform.runLater(() -> {
                            if (Main.rfid.remain_money > 0) {
                                lookup_money_label.setText(String.format("%,d", Main.rfid.remain_money) + " 원");
                                stop = true;
                                Main.rfid.remain_money = 0;
                                String path = new File("resources/msgs/msg018.wav").getAbsolutePath();
                                Main.mp.stop();
                                Main.me = new Media(new File(path).toURI().toString());
                                Main.mp = new MediaPlayer(Main.me);
                                Main.mp.play();
                                delayThread();
                            }
                        });
                    } else {
                        break;
                    }
                    try {Thread.sleep(1000);} catch (InterruptedException e) {}
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
    }

    public void delayThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {Thread.sleep(1000);} catch (InterruptedException e) {}
                Platform.runLater(() -> {
                    handleBack();
                });
            }
        };

        thread.setDaemon(true);
        thread.start();
    }

    //세차카드를 터치하여주세요 음성 재생 쓰레드
    private void handleSound() {
        sound_stop = false;
        Thread thread = new Thread() {
            @Override
            public void run() {
                while(!sound_stop) {
                    try {Thread.sleep(2300);} catch (InterruptedException e) {}
                    handlePlaySound();
                    try {Thread.sleep(3800);} catch (InterruptedException e) {}
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
    }

    public void handlePlaySound() {
        String path = new File("resources/msgs/msg010.wav").getAbsolutePath();
        Main.mp.stop();
        Main.me = new Media(new File(path).toURI().toString());
        Main.mp = new MediaPlayer(Main.me);
        Main.mp.play();
    }

    // 카드 터치 쓰레드 중지
    public void handleStopSound() {
        sound_stop = true;
    }

    public void stopThread() {
        handleStopSound();
        stop = true;
        Main.bill.stop = true;
    }

    public void handleBack() {
        try {
            stopThread();
            Main.RF_FLAG = 1;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainView.fxml"));
            AnchorPane sellerView = (AnchorPane) loader.load();
            Main.rootLayout.setCenter(sellerView);
            MainView controller = loader.getController();
            controller.setMainApp(this.mainApp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
