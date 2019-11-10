package pemilukm.teti

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.VBox
import javafx.stage.Stage
import pemilukm.teti.controls.helpers.ControlsHelper


class FinishedVoteView internal constructor() : VBox() {
    var stage: Stage? = null
    var content: Parent
    @FXML
    private val root: Parent? = null

    init {
        content = ControlsHelper.initControl(this)
    }

    fun setHotkey() {
        val scene = content.scene
        //        System.out.println(scene.getClass().getSimpleName());
        scene.accelerators[KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN)] = Runnable { backToMain() }
    }

    @FXML
    protected fun backToMain() {
        GlobalVar.primaryStage?.scene  = GlobalVar.mainScene
        GlobalVar.kiosk()
        GlobalVar.primaryStage?.show()
    }
}
