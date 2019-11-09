package pemilukm.teti;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pemilukm.teti.controls.helpers.ControlsHelper;



public class FinishedVoteView extends VBox {
    public Stage stage;
    public Parent content;
    @FXML
    private Parent root;

    FinishedVoteView() {
        content = ControlsHelper.initControl(this);
    }

    public void setHotkey(){
        Scene scene = content.getScene();
//        System.out.println(scene.getClass().getSimpleName());
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN),
                new Runnable() {
                    @Override public void run() {
                        backToMain();
                    }
                }
        );
    }

    @FXML
    protected void backToMain() {
        GlobalVar.primaryStage.setScene(GlobalVar.mainScene);
        GlobalVar.kiosk();
        GlobalVar.primaryStage.show();
    }
}
