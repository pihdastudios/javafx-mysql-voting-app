package pemilukm.teti;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pemilukm.teti.controls.helpers.ControlsHelper;

public class StartView extends VBox {
    public Stage stage;
    public Parent content;
    public VoteWindow voteWindow;

    public StartView() {
        content = ControlsHelper.initControl(this);
    }

    @FXML
    protected void onStartBtn() {
        voteWindow.adjustImg();
        voteWindow.setDisable(false);
        stage.close();
    }
}
