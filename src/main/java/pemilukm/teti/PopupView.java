package pemilukm.teti;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pemilukm.teti.controls.helpers.ControlsHelper;

import java.io.IOException;

class PopupView extends VBox {
	public Stage stage;
	public Parent content;
	public Node mainRoot;
	@FXML
	Node root;

    PopupView() {
		content = ControlsHelper.initControl(this);
	}

	@FXML
	protected void onPassword() {

	}

	@FXML
	protected void onEnterBtn() throws IOException {

		AdminView adminView = new AdminView();
		ControlsHelper.changeScene(adminView.content);
        GlobalVar.primaryStage.hide();
        GlobalVar.primaryStage.setWidth(600);
        GlobalVar.primaryStage.setHeight(400);
        GlobalVar.primaryStage.initModality(Modality.APPLICATION_MODAL);
		GlobalVar.primaryStage.show();
		stage.close();

	}

	@FXML
	protected void onBackBtn() {

	}
}
