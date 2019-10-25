package pemilukm.teti;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pemilukm.teti.controls.helpers.ControlsHelper;

import java.io.IOException;

class PopupView extends VBox {
	public Stage stage;
	public Parent content;
	public Node mainRoot;
	@FXML
	Node root;

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
