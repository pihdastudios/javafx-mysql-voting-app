package pemilukm.teti;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pemilukm.teti.controls.helpers.ControlsHelper;
import util.mysqlcon.MySqlCon;

import java.awt.*;
import java.io.IOException;

public class AdminView extends VBox {
    Parent content;
    @FXML
    private Parent root;
    private Desktop desktop = Desktop.getDesktop();
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField urlField;
    @FXML
    private TextField dbNameField;
    @FXML
    private Button connectBtn;
    @FXML
    private Button createDbBtn;
    @FXML
    private Button backupBtn;
    @FXML
    private Button openFolderBtn;
    @FXML
    private Button backBtn;
    private String dir;
    private Stage primaryStage;

    AdminView() {
        content = ControlsHelper.initControl(this);
    }

    @FXML
    protected void onUsername() {
        usernameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER){
                onConnectBtn();
            }
        });
    }

    @FXML
    protected void onConnectBtn() {
        LoadingWindow loadingWindow = new LoadingWindow();
        Stage loadingDialog = ControlsHelper.createModalStageFor(content, loadingWindow.content, "");
        loadingWindow.stage = loadingDialog;
        loadingDialog.initStyle(StageStyle.UNDECORATED);


//
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            public Boolean call() {
                setData();
                MySqlCon.startConnection();
                return true;
            }
        };

        task.setOnRunning((e) -> {
            loadingDialog.show();

        });
        task.setOnSucceeded((e) -> {
            loadingDialog.hide();
            loadingDialog.close();
        });
        new Thread(task).start();

    }

    @FXML
    protected void onBackupBtn() {
        setData();
        MySqlCon.backupDbtoSql(dir);
    }

    @FXML
    protected void onCreateDbBtn() {
        setData();
        MySqlCon.newDb();
    }

    private void setData() {
        MySqlCon.username = usernameField.getText();
        MySqlCon.password = passwordField.getText();
        MySqlCon.address = urlField.getText();

        MySqlCon.dbName = dbNameField.getText();
    }

    @FXML
    protected void onOpenFolderBtn() {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        dir = directoryChooser.showDialog(primaryStage).getAbsolutePath();
    }

    @FXML
    protected void onBackBtn() {
        GlobalVar.primaryStage.setScene(GlobalVar.mainScene);
        GlobalVar.primaryStage.setFullScreen(true);
        GlobalVar.primaryStage.show();
    }
}
