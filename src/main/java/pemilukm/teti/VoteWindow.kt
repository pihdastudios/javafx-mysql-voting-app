package pemilukm.teti

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.stage.Stage
import javafx.stage.StageStyle
import pemilukm.teti.controls.helpers.ControlsHelper

import java.io.IOException
import java.sql.SQLException


class VoteWindow internal constructor() : VBox() {
    var content: Parent
    @FXML
    private val btn_2: Button? = null
    @FXML
    private val btn_3: Button? = null
    @FXML
    private val img_2: ImageView? = null
    @FXML
    private val img_3: ImageView? = null
    @FXML
    private val stackPane_1: StackPane? = null
    @FXML
    private val stackPane_2: StackPane? = null
    @FXML
    private val stackPane_3: StackPane? = null
    @FXML
    private val dataNIM: Text? = null
    private val init: Boolean = false

    init {
        content = ControlsHelper.initControl(this)
    }

    @FXML
    fun initialize() {
        setTextNIM("")
        //		adjustImg();
    }

    @FXML
    protected fun onBackBtn() {
        //		GlobalVar.primaryStage.setScene(GlobalVar.mainScene);
        //		GlobalVar.primaryStage.setFullScreen(true);
        //		GlobalVar.primaryStage.show();
    }

    @FXML
    fun setTextNIM(s: String) {
        dataNIM!!.text = s
    }

    @FXML
    @Throws(SQLException::class)
    protected fun onCandBtn_2() {
        val popupVoteView = PopupVoteView()
        popupVoteView.setVote(2)
        val popupDialog = ControlsHelper.createModalStageFor(content, popupVoteView.content, "Prompt")
        popupVoteView.stage = popupDialog
        popupDialog.initStyle(StageStyle.UNDECORATED)
        popupDialog.height = 300.0
        popupDialog.width = 300.0
        popupDialog.show()
    }

    @FXML
    @Throws(SQLException::class)
    protected fun onCandBtn_3() {
        val popupVoteView = PopupVoteView()
        popupVoteView.setVote(3)
        val popupDialog = ControlsHelper.createModalStageFor(content, popupVoteView.content, "Prompt")
        popupVoteView.stage = popupDialog
        popupDialog.initStyle(StageStyle.UNDECORATED)
        popupDialog.height = 300.0
        popupDialog.width = 300.0
        popupDialog.show()
    }

    fun adjustImg() {
        //		img_1.setFitHeight(stackPane_1.getHeight());
        //		img_2.setFitHeight(stackPane_2.getHeight());
        //		img_3.setFitHeight(stackPane_3.getHeight());

    }

    @FXML
    protected fun onMouseEntered_2() {
        img_2!!.image = Image("img/FirhanB.jpg")
        btn_2!!.style = "-fx-background-color: rgb(16, 116, 143)"
        adjustImg()
    }

    @FXML
    protected fun onMouseEntered_3() {
        img_3!!.image = Image("img/GayongB.jpg")
        btn_3!!.style = "-fx-background-color: rgb(16, 116, 143)"
        adjustImg()
    }

    @FXML
    protected fun onMouseExited_2() {
        img_2!!.image = Image("img/FirhanA.jpg")
        btn_2!!.style = "-fx-background-color: rgb(22, 131, 160)"
        adjustImg()
    }

    @FXML
    protected fun onMouseExited_3() {
        img_3!!.image = Image("img/GayongA.jpg")
        btn_3!!.style = "-fx-background-color: rgb(22, 131, 160)"
        adjustImg()
    }

    @FXML
    protected fun vBOnMouseEntered() {
        //		if (init)
        //			adjustImg();
    }
}
