package pemilukam.teti.controls.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import pemilukm.teti.AdminView;
import pemilukm.teti.GlobalVar;
import pemilukm.teti.MainView;

import java.io.IOException;

public final class ControlsHelper {

	private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

	private ControlsHelper() {

	}

	public static Parent initControl(Node node) {
		return initControl(node, "/" + node.getClass().getSimpleName() + ".fxml");
	}

	public static Parent initControl(Node node, String fxml) {
		FXMLLoader fxmlLoader = new FXMLLoader(node.getClass().getResource(fxml));
		fxmlLoader.setController(node);

		try {
			return fxmlLoader.<Parent>load();
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
