package pemilukm.teti;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pemilukam.teti.controls.helpers.ControlsHelper;

public class MainView extends Application {

	@FXML
	private Parent root;

	@Override
	public void start(Stage primaryStage) throws IOException {
		GlobalVar.primaryStage = primaryStage;
		startWindow();
	}

	public void startWindow() throws IOException {
		root = FXMLLoader.load(getClass().getClassLoader().getResource("MainView.fxml"));
		
		Scene scene = new Scene(root, 600, 400);
		GlobalVar.mainScene = scene;
		GlobalVar.primaryStage.setTitle("Pemilu KMTETI 2019");
		GlobalVar.primaryStage.setMinWidth(600);
		GlobalVar.primaryStage.setMinHeight(400);
		GlobalVar.primaryStage.setScene(scene);
		GlobalVar.primaryStage.initStyle(StageStyle.UNDECORATED);
		GlobalVar.primaryStage.show();
	}

	public void newObj(String[] args) {
		launch(args);
	}

	@FXML
	protected void onAdminBtn() {
		PopupView popupView = new PopupView();
		Stage popupDialog = ControlsHelper.createModalStageFor(root, popupView.content, "Admin");
		popupView.stage = popupDialog;
		popupDialog.initStyle(StageStyle.UNDECORATED);
		popupDialog.show();
		
	}

	@FXML
	protected void onExitBtn() {
		Platform.exit();
		System.exit(0);
	}
}
