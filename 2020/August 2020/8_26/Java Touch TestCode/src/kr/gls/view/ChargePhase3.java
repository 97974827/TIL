package kr.gls.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import kr.gls.Main;

import java.io.File;

import static kr.gls.util.RFID.thread_stop;

public class ChargePhase3 {

    @FXML
    private Label total_charge_label;

    boolean delay_stop = false;

    public Main mainApp;

    @FXML
    private void initialize() {
        handleChargeMoney();
    }

    public void handleChargeMoney() {
        thread_stop = true;
        String path = new File("resources/msgs/msg013.wav").getAbsolutePath();
        Main.me = new Media(new File(path).toURI().toString());
        Main.mp = new MediaPlayer(Main.me);
        Main.mp.stop();
        Main.mp.play();
        int total_money = Main.rfid.total_charge_money;
        total_charge_label.setText(String.format("%,d", total_money) + " 원");
        Main.rfid.charge_state = false;
        Main.rfid.total_charge_money = 0;
        Main.bill.total_bill_money = 0;
        delayThread();
    }

    public void delayThread() {
        delay_stop = false;
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {Thread.sleep(3000);} catch (InterruptedException e) {}
                Platform.runLater(() -> {
                    delay_stop = true;
                    try {
                        Main.RF_FLAG = 1;
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(Main.class.getResource("view/MainView.fxml"));
                        AnchorPane sellerView = (AnchorPane) loader.load();
                        Main.rootLayout.setCenter(sellerView);
                        MainView controller = loader.getController();
                        controller.setMainApp(mainApp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        };

        thread.setDaemon(true);
        thread.start();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
