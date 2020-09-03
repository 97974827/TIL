package kr.gls;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import kr.gls.util.Bill;
import kr.gls.util.CommonUtil;
import kr.gls.util.HttpProxy;
import kr.gls.util.RFID;
import kr.gls.view.MainView;

public class Main extends Application {
    private Stage primaryStage;
    public static int test_count = 0;
    public static BorderPane rootLayout;

    public static Bill bill = new Bill();
    public static RFID rfid = new RFID();

    public static Media me;
    public static MediaPlayer mp;

    // 리더기 사용 상태 1:잔액조회 2:충전
    public static int RF_FLAG = 1;

    HttpProxy p = new HttpProxy();
    CommonUtil cu = new CommonUtil();

    public Main() {

    }

    @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("터치충전기");

            initRootLayout();
            showMainOverview();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMainOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainView.fxml"));
            AnchorPane mainView = (AnchorPane) loader.load();
            rootLayout.setCenter(mainView);
            MainView controller = loader.getController();
            controller.setMainApp(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
