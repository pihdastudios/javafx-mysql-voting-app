package pemilukm.teti;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pemilukm.teti.controls.helpers.ControlsHelper;
import util.mysqlcon.MySqlCon;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class MainView extends Application {

    @FXML
    private Parent root;
    @FXML
    private ImageView img_1;
    @FXML
    private TextField hashFields;
    @FXML
    private Text cekNIM_Fields;

    @Override
    public void start(Stage primaryStage) throws IOException {
        GlobalVar.primaryStage = primaryStage;
        startWindow();
    }

    private void startWindow() throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/MainView.fxml")));

        Scene scene = new Scene(root);
        GlobalVar.mainScene = scene;
        GlobalVar.primaryStage.setTitle("Pemilu KMTETI 2019");
        GlobalVar.primaryStage.setMinWidth(600);
        GlobalVar.primaryStage.setMinHeight(400);
        GlobalVar.primaryStage.setScene(scene);
        GlobalVar.primaryStage.setFullScreen(true);
        GlobalVar.primaryStage.show();
    }

    void newObj(String[] args) {
        launch(args);
    }

    @FXML
    protected void onEnterBtn() throws SQLException {
        LoadingWindow loadingWindow = new LoadingWindow();
        Stage loadingDialog = ControlsHelper.createModalStageFor(root, loadingWindow.content, "");
        loadingWindow.stage = loadingDialog;
        loadingDialog.initStyle(StageStyle.UNDECORATED);
        loadingDialog.setHeight(300);
        loadingDialog.setWidth(300);
        loadingDialog.show();
        if (GlobalVar.connActive == false) {
            cekNIM_Fields.setText("Database belum terkoneksi!");
        } else if (MySqlCon.checkNIM(hashFields.getText())) {
            cekNIM_Fields.setText("NIM Telah Memilih!");
        } else {
            cekNIM_Fields.setText("");
            VoteWindow voteWindow = new VoteWindow();
            ControlsHelper.changeScene(voteWindow.content);
            GlobalVar.primaryStage.hide();
            GlobalVar.hashNIM = hashFields.getText();
            GlobalVar.primaryStage.setFullScreen(true);
            GlobalVar.primaryStage.show();
        }
        loadingWindow.setLabelText("Done");
        loadingDialog.close();
    }

    @FXML
    protected void onAdminBtn() {
        PopupView popupView = new PopupView();
        Stage popupDialog = ControlsHelper.createModalStageFor(root, popupView.content, "Admin");
        popupView.stage = popupDialog;
        popupDialog.initStyle(StageStyle.UNDECORATED);
        popupDialog.setHeight(300);
        popupDialog.setWidth(300);
        popupDialog.show();
    }

    @FXML
    protected void onExitBtn() {
        Platform.exit();
        System.exit(0);
    }
}
