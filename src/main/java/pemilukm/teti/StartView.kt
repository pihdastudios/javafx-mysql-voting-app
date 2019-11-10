package pemilukm.teti

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.layout.VBox
import javafx.stage.Stage
import pemilukm.teti.controls.helpers.ControlsHelper

class StartView : VBox() {
    var stage: Stage? = null
    var content: Parent
    var voteWindow: VoteWindow? = null

    init {
        content = ControlsHelper.initControl(this)
    }

    @FXML
    protected fun onStartBtn() {
        voteWindow!!.adjustImg()
        voteWindow!!.isDisable = false
        stage!!.close()
    }
}
