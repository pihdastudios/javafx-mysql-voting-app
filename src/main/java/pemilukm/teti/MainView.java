package pemilukm.teti;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pemilukam.teti.controls.helpers.ControlsHelper;
import pemilukm.teti.AdminView;

public class MainView extends Application {

	private Stage primaryStage;
	@FXML
	private Parent root;

	@Override
	public void start(Stage pPrimaryStage) throws Exception {
		root = FXMLLoader.load(getClass().getClassLoader().getResource("MainView.fxml"));
		primaryStage = pPrimaryStage;
		Scene scene = new Scene(root, 600, 400);
		pPrimaryStage.setTitle("Pemilu KMTETI 2019");
		pPrimaryStage.setMinWidth(600);
		pPrimaryStage.setMinHeight(400);
		pPrimaryStage.setScene(scene);
		pPrimaryStage.show();
	}

	public void newObj(String[] args) {
		launch(args);
	}

	@FXML
	protected void onAdminBtn() {
		PopupView popupView = new PopupView();
		Stage popupDialog = ControlsHelper.createModalStageFor(root, popupView.content, "Admin");

		popupDialog.show();
		
	}

	@FXML
	protected void onExitBtn() {
		Platform.exit();
		System.exit(0);
	}
}
