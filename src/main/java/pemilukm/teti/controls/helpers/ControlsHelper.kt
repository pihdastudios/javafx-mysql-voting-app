package pemilukm.teti.controls.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pemilukm.teti.GlobalVar;

import java.io.IOException;

public final class ControlsHelper {

    private ControlsHelper() {

    }

    public static Parent initControl(Node node) {
        return initControl(node, "/fxml/" + node.getClass().getSimpleName() + ".fxml");
    }

    public static Parent initControl(Node node, String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(node.getClass().getResource(fxml));
        fxmlLoader.setController(node);

        try {
            return fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static Stage createModalStageFor(Node parent, Parent content, String title) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parent.getScene().getWindow());
        stage.setScene(new Scene(content));
        return stage;
    }

    public static void changeScene(Parent content) {
        GlobalVar.primaryStage.setScene(new Scene(content));
    }
}
