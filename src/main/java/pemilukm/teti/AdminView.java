package pemilukm.teti;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pemilukm.teti.controls.helpers.ControlsHelper;
import util.mysqlcon.MySqlCon;

import java.awt.*;

public class AdminView extends VBox {
	public Parent content;
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

	public AdminView() {
		content = ControlsHelper.initControl(this);
	}

	@FXML
	public void setUsername() {
		MySqlCon.username = usernameField.getText();
	}

	@FXML
	public void setPassword() {
		MySqlCon.password = passwordField.getText();
	}

	@FXML
	public void setURL() {
		MySqlCon.address = urlField.getText();
	}

	@FXML
	public void setDbName() {
		MySqlCon.password = dbNameField.getText();
	}

	@FXML
	protected void onConnectBtn(ActionEvent event) {
		MySqlCon.startConnection();
	}

	@FXML
	protected void onBackupBtn(ActionEvent event) {
		MySqlCon.backupDbtoSql(dir);
	}

	@FXML
	protected void onCreateDbBtn(ActionEvent event) {
		MySqlCon.newDb();
	}

	@FXML
	protected void onOpenFolderBtn(ActionEvent event) {
		final DirectoryChooser directoryChooser = new DirectoryChooser();
		String directory = directoryChooser.showDialog(primaryStage).getAbsolutePath();
	}

	@FXML
	protected void onBackBtn() {
		GlobalVar.primaryStage.setScene(GlobalVar.mainScene);
		GlobalVar.primaryStage.show();
	}
}
