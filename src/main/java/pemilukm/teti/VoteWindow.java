package pemilukm.teti;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pemilukm.teti.controls.helpers.ControlsHelper;

import java.io.IOException;
import java.sql.SQLException;


public class VoteWindow extends VBox {
	public Parent content;
	@FXML
	private ImageView img_1;
	@FXML
	private ImageView img_2;
	@FXML
	private ImageView img_3;
	@FXML
	private StackPane stackPane_1;
	@FXML
	private StackPane stackPane_2;
	@FXML
	private StackPane stackPane_3;
	private boolean init;

	VoteWindow() {
		content = ControlsHelper.initControl(this);

	}

	@FXML
	public void initialize() {
//		adjustImg();
	}

	@FXML
	protected void onBackBtn() {
//		GlobalVar.primaryStage.setScene(GlobalVar.mainScene);
//		GlobalVar.primaryStage.setFullScreen(true);
//		GlobalVar.primaryStage.show();
	}

	@FXML
	protected void onCandBtn_1() throws SQLException {
		PopupVoteView popupVoteView = new PopupVoteView();
		popupVoteView.setVote(1);
		Stage popupDialog = ControlsHelper.createModalStageFor(content, popupVoteView.content, "Prompt");
		popupVoteView.stage = popupDialog;
		popupDialog.initStyle(StageStyle.UNDECORATED);
		popupDialog.setHeight(300);
		popupDialog.setWidth(300);
		popupDialog.show();
	}

	@FXML
	protected void onCandBtn_2() throws SQLException {
		PopupVoteView popupVoteView = new PopupVoteView();
		popupVoteView.setVote(2);
		Stage popupDialog = ControlsHelper.createModalStageFor(content, popupVoteView.content, "Prompt");
		popupVoteView.stage = popupDialog;
		popupDialog.initStyle(StageStyle.UNDECORATED);
		popupDialog.setHeight(300);
		popupDialog.setWidth(300);
		popupDialog.show();
	}

	@FXML
	protected void onCandBtn_3() throws SQLException {
		PopupVoteView popupVoteView = new PopupVoteView();
		popupVoteView.setVote(3);
		Stage popupDialog = ControlsHelper.createModalStageFor(content, popupVoteView.content, "Prompt");
		popupVoteView.stage = popupDialog;
		popupDialog.initStyle(StageStyle.UNDECORATED);
		popupDialog.setHeight(300);
		popupDialog.setWidth(300);
		popupDialog.show();
	}

	public void adjustImg() {
//		img_1.setFitHeight(stackPane_1.getHeight());
//		img_2.setFitHeight(stackPane_2.getHeight());
//		img_3.setFitHeight(stackPane_3.getHeight());

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

	@FXML
	protected void vBOnMouseEntered() {
//		if (init)
//			adjustImg();
	}
}
