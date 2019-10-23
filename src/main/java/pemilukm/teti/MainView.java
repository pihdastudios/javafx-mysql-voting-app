package pemilukm.teti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {
    private VBox mainVbox;

    @Override
    public void start(Stage pPrimaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainView.fxml"));
        Scene scene = new Scene(root, 300, 275);
        pPrimaryStage.setTitle("FXML Welcome");
        pPrimaryStage.setScene(scene);
        pPrimaryStage.show();
    }

    public void newObj(String[] args) {
        launch(args);
    }
}
