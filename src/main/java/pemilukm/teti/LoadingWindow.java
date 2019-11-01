package pemilukm.teti;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pemilukm.teti.controls.helpers.ControlsHelper;

public class LoadingWindow extends VBox {
    public Stage stage;
    public Parent content;
    @FXML
    private Label mainLabel;

    LoadingWindow() {
        content = ControlsHelper.initControl(this);
    }

    public void setLabelText(String text) {
        mainLabel.setText(text);
    }

}
