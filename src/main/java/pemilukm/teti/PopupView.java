package pemilukm.teti;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pemilukm.teti.controls.helpers.ControlsHelper;

import java.io.IOException;

class PopupView extends VBox {
	public Stage stage;
	public Parent content;
	public Node mainRoot;
	@FXML
	Node root;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Text cekPass_Fields;

    PopupView() {
		content = ControlsHelper.initControl(this);
	}

	@FXML
	protected void onPassword() {

	}

	@FXML
	protected void onEnterBtn() throws IOException {
		if(passwordField.getText().equals(GlobalVar.passAdmin)){
			cekPass_Fields.setText("");
			AdminView adminView = new AdminView();
			ControlsHelper.changeScene(adminView.content);
			GlobalVar.primaryStage.hide();
			GlobalVar.primaryStage.setWidth(600);
			GlobalVar.primaryStage.setHeight(400);
			GlobalVar.primaryStage.show();
			stage.close();
		} else{
			cekPass_Fields.setText("Password salah!");
		}
	}

	@FXML
	protected void onBackBtn() {
        stage.hide();
	}
}
