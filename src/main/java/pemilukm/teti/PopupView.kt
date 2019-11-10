package pemilukm.teti

import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.PasswordField
import javafx.scene.input.KeyCode
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.stage.Stage
import pemilukm.teti.controls.helpers.ControlsHelper

import java.io.IOException

internal class PopupView : VBox() {
    var stage: Stage? = null
    var content: Parent
    var mainRoot: Node? = null
    @FXML
    var root: Node? = null
    @FXML
    private val passwordField: PasswordField? = null
    @FXML
    private val cekPass_Fields: Text? = null

    init {
        content = ControlsHelper.initControl(this)
    }

    @FXML
    protected fun onPassword() {
        passwordField?.setOnKeyPressed { event ->
            if (event.code == KeyCode.ENTER) {
                try {
                    onEnterBtn()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

    @FXML
    @Throws(IOException::class)
    protected fun onEnterBtn() {
        if (passwordField != null) {
            if (passwordField.text == GlobalVar.passAdmin) {
                cekPass_Fields!!.text = ""
                val adminView = AdminView()
                ControlsHelper.changeScene(adminView.content)
                GlobalVar.primaryStage!!.hide()
                GlobalVar.primaryStage!!.width = 600.0
                GlobalVar.primaryStage!!.height = 400.0
                GlobalVar.primaryStage!!.show()
                stage!!.close()
            } else {
                cekPass_Fields!!.text = "Password salah!"
            }
        }
    }

    @FXML
    protected fun onBackBtn() {
        stage!!.hide()
    }
}
