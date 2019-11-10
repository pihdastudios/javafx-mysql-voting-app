package pemilukm.teti

import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCombination
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.stage.Stage
import pemilukm.teti.controls.helpers.ControlsHelper
import util.mysqlcon.MySqlCon

import java.io.IOException
import java.sql.SQLException

internal class PopupVoteView : VBox() {
    var stage: Stage? = null
    var content: Parent
    var mainRoot: Node? = null
    @FXML
    var root: Node? = null
    @FXML
    private val passwordField: PasswordField? = null
    @FXML
    private val cekPass_Fields: Text? = null
    @FXML
    private val voteLabel: Label? = null

    init {
        content = ControlsHelper.initControl(this)
    }

    fun setVote(`val`: Int) {
        voteLabel!!.text = "Anda memilih calon nomor urut $`val`"
        voteCand = `val`
    }

    //    @FXML
    //    protected void onPassword() {
    //        passwordField.setOnKeyPressed(event -> {
    //            if (event.getCode() == KeyCode.ENTER){
    //                try{
    //                    onEnterBtn();
    //                }
    //                catch (IOException e){
    //                    e.printStackTrace();
    //                }
    //            }
    //        });
    //    }


    @FXML
    @Throws(SQLException::class)
    protected fun onYesBtn() {
        GlobalVar.valueNIM?.let { MySqlCon.addVote(it, voteCand) }
        val finishedVoteView = FinishedVoteView()
        ControlsHelper.changeScene(finishedVoteView.content)
        finishedVoteView.setHotkey()
        GlobalVar.kiosk()
        GlobalVar.primaryStage!!.show()
        stage!!.close()
    }

    @FXML
    protected fun onBackBtn() {
        stage!!.hide()
    }

    companion object {
        private var voteCand: Int = 0
    }
}
