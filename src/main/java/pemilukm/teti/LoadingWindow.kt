package pemilukm.teti

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage
import pemilukm.teti.controls.helpers.ControlsHelper

class LoadingWindow internal constructor() : VBox() {
    var stage: Stage? = null
    var content: Parent
    @FXML
    private val mainLabel: Label? = null

    init {
        content = ControlsHelper.initControl(this)
    }

    fun setLabelText(text: String) {
        mainLabel!!.text = text
    }

}
