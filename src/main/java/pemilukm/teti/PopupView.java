package pemilukm.teti;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pemilukam.teti.controls.helpers.ControlsHelper;

class PopupView extends VBox {
	private Stage primaryStage;
	public Parent content;

        public PopupView() {
                content = ControlsHelper.initControl(this);
	}
	@FXML protected void onPassword() {
		
	}
	@FXML protected void onEnterBtn() {
		
	}
	@FXML protected void onBackBtn() {
		
	}
}
