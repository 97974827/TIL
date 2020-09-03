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
import kr.gls.util.Bill;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static kr.gls.util.RFID.thread_stop;

public class MainView {
    @FXML
    public ImageView charge_btn;

    @FXML
    private ImageView issued_btn;

    @FXML
    private ImageView lookup_btn;

    @FXML
    private ImageView main_label;

    @FXML
    private Label main_view_money_label;

    @FXML
    private MediaView media_view;

    @FXML
    private Label admin_login_label;

    private Main mainApp;

    private boolean stop;

    private boolean rf_stop;

    private boolean charge_btn_image_stop = false;

    private boolean lookup_btn_image_stop = false;

    @FXML
    private void initialize() {
        if (Main.bill.stop) {
            Main.bill.stop = false;
        }

        if(thread_stop) {
            thread_stop = false;
            Main.rfid.handleReader();
        }

        Main.bill.writeBlock("hi");
        Main.bill.writeBlock("enable");
        handleMainLabel();
        getBillState();
        getLookupState();
    }

    // 지폐인식기 상태 검사
    private void getBillState() {
        setChargeBtnImage();
        setLookupBtnImage();
    }

    private void setChargeBtnImage() {
        Thread thread = new Thread() {
            Image charge_enable_image;
            int bill_money = 0;
            @Override
            public void run() {
                while(true) {
                    if (!charge_btn_image_stop) {
                        if (Main.bill.bill_connected) {
                            charge_enable_image = new Image("File:../../../resources/charge_on_btn.png");

                            charge_btn.setOnMousePressed(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    handleChargeBtnPressed();
                                }
                            });
                            bill_money = Main.bill.total_bill_money;

                        } else {
                            charge_enable_image = new Image("File:../../../resources/charge_off_btn.png");
                            System.out.println("지폐인식기가 연결되지 않았습니다.");
                        }

                        Platform.runLater(() -> {
                            charge_btn.setImage(charge_enable_image);
                            main_view_money_label.setText("투입 금액 : " + String.format("%,d", bill_money) + " 원");
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

    private void setLookupBtnImage() {
        Thread thread = new Thread() {
            Image lookup_enable_image;
            @Override
            public void run() {
                while(true) {
                    if (!lookup_btn_image_stop) {
                        if (Main.rfid.rf_connected) {
                            lookup_enable_image = new Image("File:../../../resources/lookup_on_btn.png");

                            lookup_btn.setOnMousePressed(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    handleLookupBtnReleased();
                                }
                            });

                        } else {
                            lookup_enable_image = new Image("File:../../../resources/lookup_off_btn.png");
                            System.out.println("RFID 리더기가 연결되지 않았습니다.");
                        }

                        Platform.runLater(() -> {
                            lookup_btn.setImage(lookup_enable_image);
                        });
                    } else {

                    }


                    try {Thread.sleep(1000);} catch (InterruptedException e) {}
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
    }

    // 잔액조회 쓰레드
    private void getLookupState() {
        rf_stop = false;
        Thread thread = new Thread() {
            @Override
            public void run() {
                while(!rf_stop) {
                    Platform.runLater(() -> {
                        if (Main.rfid.remain_money > 0) {
                            stopThread();
                            handleLookupBtnReleased();
                        }
                    });
                    try{Thread.sleep(1000);}catch (Exception e) {}
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    // "이용하실 버튼을 눌러주세요" 핸들링
    private void handleMainLabel() {
        stop = false;

        Thread thread = new Thread() {
            @Override
            public void run() {
                boolean transe = false;
                while(!stop) {
                    Platform.runLater(() -> {
                        if (main_label.isVisible()) {
                            main_label.setVisible(false);
                        } else {
                            main_label.setVisible(true);
                        }
                    });
                    try {Thread.sleep(1000);} catch (InterruptedException e) {}
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
    }

    // [카드충전] 버튼 눌렀을 때
    @FXML
    private void handleChargeBtnPressed() {
        stopThread();

        String path = new File("../../../resources/msgs/msg005.mp3").getAbsolutePath();
        Main.me = new Media(new File(path).toURI().toString());
        Main.mp = new MediaPlayer(Main.me);
        media_view.setMediaPlayer(Main.mp);
        Main.mp.play();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ChargePhase1.fxml"));
            AnchorPane sellerView = (AnchorPane) loader.load();
            Main.rootLayout.setCenter(sellerView);
            ChargePhase1 controller = loader.getController();
            controller.setMainApp(this.mainApp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // [카드충전] 버튼 뗐을 때
    @FXML
    private void handleChargeBtnReleased() {

    }

    // [카드발급] 버튼 눌렀을 때
    @FXML
    private void handleIssuedBtnPressed() {

    }

    // [카드발급] 버튼 뗐을 때
    @FXML
    private void handleIssuedBtnReleased() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/IssuedPhase1.fxml"));
            AnchorPane sellerView = (AnchorPane) loader.load();
            Main.rootLayout.setCenter(sellerView);
            ChargePhase1 controller = loader.getController();
            controller.setMainApp(this.mainApp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // [잔액조회] 버튼 눌렀을 때
    @FXML
    private void handleLookupBtnPressed() {

    }

    // [잔액조회] 버튼 뗐을 때
    @FXML
    private void handleLookupBtnReleased() {
        if (Main.rfid.remain_money <= 0) {
            String path = new File("../../../resources/msgs/msg007.wav").getAbsolutePath();
            Main.me = new Media(new File(path).toURI().toString());
            Main.mp = new MediaPlayer(Main.me);
            media_view.setMediaPlayer(Main.mp);
            Main.mp.play();
        }

        try {
            stopThread();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Lookup.fxml"));
            AnchorPane sellerView = (AnchorPane) loader.load();
            Main.rootLayout.setCenter(sellerView);
            Lookup controller = loader.getController();
            controller.setMainApp(this.mainApp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopThread() {
//        Main.bill.thread.threadStop(true);
        charge_btn_image_stop = true;
        lookup_btn_image_stop = true;
        stop = true;
        rf_stop = true;
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
