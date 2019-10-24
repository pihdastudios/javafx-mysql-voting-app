package pemilukm.teti;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pemilukam.teti.controls.helpers.ControlsHelper;

class PopupView extends VBox {
	public Stage stage;
	public Parent content;
	public Node mainRoot;
	@FXML Node root;

	public PopupView() {
		content = ControlsHelper.initControl(this);
	}

	@FXML
	protected void onPassword() {

	}

	@FXML
	protected void onEnterBtn() throws IOException {
		
		AdminView adminView = new AdminView();
		ControlsHelper.changeScene(adminView.content);
		GlobalVar.primaryStage.show();
		stage.close();

	}

	@FXML
	protected void onBackBtn() {

	}
}
