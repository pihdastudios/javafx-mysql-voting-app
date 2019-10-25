package pemilukm.teti;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import pemilukm.teti.controls.helpers.ControlsHelper;

import java.io.IOException;


public class VoteWindow extends VBox {
	public Parent content;
	@FXML
	private ImageView img_1;
	@FXML
	private ImageView img_2;
	@FXML
	private ImageView img_3;
	@FXML
	private VBox vBox_1;
	@FXML
	private VBox vBox_2;
	@FXML
	private VBox vBox_3;

	public VoteWindow() {
		content = ControlsHelper.initControl(this);
	}

	@FXML
	protected void onBackBtn() {
		GlobalVar.primaryStage.setScene(GlobalVar.mainScene);
		GlobalVar.primaryStage.show();
	}

	@FXML
	protected void onCandBtn_1() {

	}

	@FXML
	protected void onCandBtn_2() {

	}

	@FXML
	protected void onCandBtn_3() {

	}

	public void adjustImg() {
		img_1.setFitHeight(vBox_1.getHeight());
		img_2.setFitHeight(vBox_2.getHeight());
		img_3.setFitHeight(vBox_3.getHeight());

	}

	@FXML
	protected void onMouseEntered_1() throws IOException {
		img_1.setImage(new Image("img/DewanB.jpg"));
		adjustImg();
	}

	@FXML
	protected void onMouseEntered_2() {
		img_2.setImage(new Image("img/FirhanB.jpg"));
		adjustImg();

	}

	@FXML
	protected void onMouseEntered_3() {
		img_3.setImage(new Image("img/GayongB.jpg"));
		adjustImg();

	}

	@FXML
	protected void onMouseExited_1() {
		img_1.setImage(new Image("img/DewanA.jpg"));
		adjustImg();

	}

	@FXML
	protected void onMouseExited_2() {
		img_2.setImage(new Image("img/FirhanA.jpg"));
		adjustImg();

	}

	@FXML
	protected void onMouseExited_3() {
		img_3.setImage(new Image("img/GayongA.jpg"));
		adjustImg();

	}
}
