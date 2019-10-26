package pemilukm.teti;

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
	Parent content;
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

//	@FXML
//	public void setUsername() {
//		MySqlCon.username = usernameField.getText();
//	}
//
//	@FXML
//	public void setPassword() {
//		MySqlCon.password = passwordField.getText();
//	}
//
//	@FXML
//	public void setURL() {
//		MySqlCon.address = urlField.getText();
//	}
//
//	@FXML
//	public void setDbName() {
//		MySqlCon.dbName = dbNameField.getText();
//	}

	@FXML
	protected void onConnectBtn() {
		setData();
		MySqlCon.startConnection();
	}

	@FXML
	protected void onBackupBtn() {
		MySqlCon.backupDbtoSql(dir);
	}

	@FXML
	protected void onCreateDbBtn() {
		setData();
		MySqlCon.newDb();
	}

	private void setData() {
		System.out.println(usernameField.getText());
		MySqlCon.username = usernameField.getText();
		MySqlCon.password = passwordField.getText();
		MySqlCon.address = urlField.getText();

		MySqlCon.dbName = dbNameField.getText();
	}

	@FXML
	protected void onOpenFolderBtn() {
		final DirectoryChooser directoryChooser = new DirectoryChooser();
		String directory = directoryChooser.showDialog(primaryStage).getAbsolutePath();
	}

	@FXML
	protected void onBackBtn() {
		GlobalVar.primaryStage.setScene(GlobalVar.mainScene);
		GlobalVar.primaryStage.setFullScreen(true);
		GlobalVar.primaryStage.show();
	}
}
