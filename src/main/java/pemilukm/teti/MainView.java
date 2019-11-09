package pemilukm.teti;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
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
    private TextField nimFields;
    @FXML
    private Text cekNIM_Fields;
    @FXML
    private PasswordField mainPassFields;

    @Override
    public void start(Stage primaryStage) throws IOException {
        GlobalVar.primaryStage = primaryStage;
        startWindow();
    }

    private void startWindow() throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/MainView.fxml")));

        Scene scene = new Scene(root);
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN),
                new Runnable() {
                    @Override public void run() {
                        onAdminBtn();
                    }
                }
        );
        Platform.setImplicitExit(false);
        GlobalVar.mainScene = scene;
        GlobalVar.primaryStage.setTitle("Pemilu KMTETI 2019");
        GlobalVar.primaryStage.setAlwaysOnTop(true);
//        GlobalVar.primaryStage.setMinWidth(600);
//        GlobalVar.primaryStage.setMinHeight(400);
        GlobalVar.primaryStage.setScene(scene);
        GlobalVar.kiosk();
        GlobalVar.primaryStage.show();
    }

    void newObj(String[] args) {
        launch(args);
    }

    @FXML
    protected void onNIM() {
        nimFields.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    onEnterBtn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    protected void onPass() {
        nimFields.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    onEnterBtn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    protected void onEnterBtn() throws SQLException {
        if (mainPassFields.getText().equals(GlobalVar.passMain)) {
            LoadingWindow loadingWindow = new LoadingWindow();
            Stage loadingDialog = ControlsHelper.createModalStageFor(root, loadingWindow.content, "");
            loadingWindow.stage = loadingDialog;
            loadingDialog.initStyle(StageStyle.UNDECORATED);
            loadingDialog.setHeight(300);
            loadingDialog.setWidth(300);
            loadingDialog.show();
            if (GlobalVar.connActive == false) {
                cekNIM_Fields.setText("Database belum terkoneksi!");
            } else {

                System.out.println("Koneksi -> " + MySqlCon.cekConn());

                int cek = MySqlCon.cekNIM(nimFields.getText());

                if (cek == 0) {
                    cekNIM_Fields.setText("NIM Tidak Valid!");
                } else if (cek == 2) {
                    cekNIM_Fields.setText("NIM Telah Memilih!");
                } else {
                    VoteWindow voteWindow = new VoteWindow();
                    ControlsHelper.changeScene(voteWindow.content);
                    voteWindow.setTextNIM(GlobalVar.textNIM);
//                    GlobalVar.primaryStage.hide();
                    GlobalVar.valueNIM = nimFields.getText();
                    GlobalVar.kiosk();
                    GlobalVar.primaryStage.show();
                    nimFields.setText("");
                    mainPassFields.setText("");
                    cekNIM_Fields.setText("");
                }
            }
            loadingWindow.setLabelText("Done");
            loadingDialog.close();
        } else {
            cekNIM_Fields.setText("Password salah!");
        }
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
}
