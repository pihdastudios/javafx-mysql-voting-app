package pemilukm.teti

import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.KeyCombination
import javafx.stage.Stage
import javafx.stage.WindowEvent

object GlobalVar {
    var primaryStage: Stage? = null
    var mainScene: Scene? = null
    var valueNIM: String? = null
    var textNIM: String? = null
    var connActive: Boolean = false
    var AESKey = "<]pemilu!TETI19*"
    var passAdmin = "semangat"
    var passMain = "19lagi"

    fun kiosk() {
        primaryStage!!.isAlwaysOnTop = true
        //primaryStage.setMaximized(true);
        primaryStage!!.isFullScreen = true
        primaryStage!!.fullScreenExitHint = null
        primaryStage!!.fullScreenExitKeyCombination = KeyCombination.NO_MATCH
        primaryStage!!.onCloseRequest = EventHandler { event -> event.consume() }
    }
}