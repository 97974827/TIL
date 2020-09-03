package kr.gls.view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import kr.gls.Main;

import java.io.File;

public class ChargePhase2 {
    @FXML
    private Label charge_money_label;

    @FXML
    private Label charge_bonus_label;

    @FXML
    private ImageView back_btn;

    public boolean stop = false;

    boolean sound_stop = false;

    boolean charge_stop = false;

    public Main mainApp;

    @FXML
    private void initialize() {
        setChargeMoneyLabel();
        handleSound();
        if (Main.bill.stop) {
            Main.bill.stop = false;
            Main.bill.activeStateThread();
        }
        handleCharge();
    }

    public void handleCharge() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (!charge_stop) {
                        if (Main.rfid.charge_state) {
                            charge_stop = true;
                            Main.bill.stop = true;
                        }
                        Platform.runLater(() -> {
                            if (Main.rfid.charge_state) {
                                try {
                                    stopThread();
                                    Main.RF_FLAG = 1;
                                    handleStopSound();
                                    FXMLLoader loader = new FXMLLoader();
                                    loader.setLocation(Main.class.getResource("view/ChargePhase3.fxml"));
                                    AnchorPane sellerView = (AnchorPane) loader.load();
                                    Main.rootLayout.setCenter(sellerView);
                                    ChargePhase3 controller = loader.getController();
                                    controller.setMainApp(mainApp);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
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

    // 투입금액 변경되는 부분
    private void setChargeMoneyLabel() {
        Thread thread = new Thread() {
            int bill_money = 0;

            @Override
            public void run() {
                while(true) {
                    if (!stop) {
                        if (Main.bill.bill_connected) {
                            bill_money = Main.bill.total_bill_money;
                        }

                        Platform.runLater(() -> {
                            charge_money_label.setText(String.format("%,d", bill_money) + " 원");
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

    //세차카드를 터치하여주세요 음성 재생 쓰레드
    private void handleSound() {
        sound_stop = false;
        Thread thread = new Thread() {
            int bill_money = 0;
            @Override
            public void run() {
                while(true) {
                    if (!sound_stop) {
                        String path = new File("resources/msgs/msg010.wav").getAbsolutePath();
                        Main.me = new Media(new File(path).toURI().toString());
                        Main.mp = new MediaPlayer(Main.me);
                        Main.mp.stop();
                        Main.mp.play();
                        try {Thread.sleep(3800);} catch (InterruptedException e) {}
                    }
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
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

    @FXML
    private void handleBack() {
        try {
            Main.RF_FLAG = 1;
            stop = true;
//            handleStopSound();
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
