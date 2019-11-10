package pemilukm.teti.controls.helpers

import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Modality
import javafx.stage.Stage
import pemilukm.teti.GlobalVar

import java.io.IOException

object ControlsHelper {

    @JvmOverloads
    fun initControl(node: Node, fxml: String = "/fxml/" + node.javaClass.simpleName + ".fxml"): Parent {
        val fxmlLoader = FXMLLoader(node.javaClass.getResource(fxml))
        fxmlLoader.setController(node)

        try {
            return fxmlLoader.load()
        } catch (exception: IOException) {
            throw RuntimeException(exception)
        }

    }

    fun createModalStageFor(parent: Node, content: Parent, title: String): Stage {
        val stage = Stage()
        stage.title = title
        stage.initModality(Modality.APPLICATION_MODAL)
        stage.initOwner(parent.scene.window)
        stage.scene = Scene(content)
        return stage
    }

    fun changeScene(content: Parent) {
        GlobalVar.primaryStage!!.scene = Scene(content)
    }
}
