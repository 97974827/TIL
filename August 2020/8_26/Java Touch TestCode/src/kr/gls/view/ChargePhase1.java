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
import javafx.scene.media.MediaView;
import kr.gls.Main;

import java.io.File;

public class ChargePhase1 {
    @FXML
    private ImageView back_btn;

    @FXML
    private ImageView next_btn;

    @FXML
    private Label charge_money_label;

    public Main mainApp;

    public boolean sound_stop;

    public boolean stop = false;

    public boolean charge_label_stop = false;

    @FXML
    private void initialize() {
        Main.RF_FLAG = 2;
        setChargeMoneyLabel();

        if (Main.bill.stop) {
            Main.bill.stop = false;
            Main.bill.activeStateThread();
        }

        handleSound();
        handleCharge();
    }

    public void handleCharge() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
//                    if (!stop) {
//                        if (Main.rfid.charge_state) {
//                            stop = true;
//                            Main.bill.stop = true;
//                        }
//                        Platform.runLater(() -> {
//                            if (Main.rfid.charge_state) {
//                                try {
//                                    stopThread();
//                                    Main.RF_FLAG = 1;
//                                    handleStopSound();
//                                    FXMLLoader loader = new FXMLLoader();
//                                    loader.setLocation(Main.class.getResource("view/ChargePhase3.fxml"));
//                                    AnchorPane sellerView = (AnchorPane) loader.load();
//                                    Main.rootLayout.setCenter(sellerView);
//                                    ChargePhase3 controller = loader.getController();
//                                    controller.setMainApp(mainApp);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//                    } else {
//                        break;
//                    }
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
            Image next_btn_image = new Image("File:../../../resources/next_btn_off.png");
            double next_btn_width = next_btn.getFitWidth();
            double next_btn_height = next_btn.getFitHeight();
            double next_btn_layout_x = next_btn.getLayoutX();
            double next_btn_layout_y = next_btn.getLayoutY();

            @Override
            public void run() {
                while (true) {
                    if (!charge_label_stop) {
                        if (Main.bill.bill_connected) {
                            bill_money = Main.bill.total_bill_money;
                        }

                        if(bill_money > 0) {
                            next_btn_image = new Image("File:../../../resources/next_btn_ani2.gif");
                            next_btn_height = 207.0;
                            next_btn_width = 207.0;
                            next_btn_layout_x = 805.0;
                            next_btn_layout_y = 630.0;

                            next_btn.setOnMousePressed(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    handleNext();
                                }
                            });
                        }

                        Platform.runLater(() -> {
                            charge_money_label.setText(String.format("%,d", bill_money) + " 원");
                            next_btn.setImage(next_btn_image);
                            next_btn.setFitWidth(next_btn_width);
                            next_btn.setFitHeight(next_btn_height);
                            next_btn.setLayoutX(next_btn_layout_x);
                            next_btn.setLayoutY(next_btn_layout_y);
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

    //지폐를 투입하여주세요 음성 재생 쓰레드
    private void handleSound() {
        sound_stop = false;
        Thread thread = new Thread() {
            int bill_money = 0;
            @Override
            public void run() {
                try {Thread.sleep(3500);} catch (InterruptedException e) {}
                while(true) {
                    if (!sound_stop) {
                        if (Main.bill.bill_connected) {
                            bill_money = Main.bill.total_bill_money;
                        }

                        String path;
                        if (bill_money > 0) {
                            path = new File("../../../resources/msgs/msg009.wav").getAbsolutePath();
                        } else {
                            path = new File("../../../resources/msgs/msg008.wav").getAbsolutePath();
                        }
                        Main.me = new Media(new File(path).toURI().toString());
                        Main.mp = new MediaPlayer(Main.me);
                        Main.mp.play();
                        try {Thread.sleep(3800);} catch (InterruptedException e) {}
                    }
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
    }

    // 지폐 투입 쓰레드 중지
    public void handleStopSound() {
        sound_stop = true;
    }

    public void stopThread() {
        handleStopSound();
        Main.bill.stop = true;
        charge_label_stop = true;
        stop = true;
    }

    // 뒤로가기
    @FXML
    private void handleBack() {
        try {
            Main.RF_FLAG = 1;
            stopThread();
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

    // 다음
    @FXML
    private void handleNext() {
        try {
            stopThread();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ChargePhase2.fxml"));
            AnchorPane sellerView = (AnchorPane) loader.load();
            Main.rootLayout.setCenter(sellerView);
            ChargePhase2 controller = loader.getController();
            controller.setMainApp(this.mainApp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}

