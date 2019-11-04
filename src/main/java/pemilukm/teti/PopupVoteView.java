package pemilukm.teti;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pemilukm.teti.controls.helpers.ControlsHelper;
import util.mysqlcon.MySqlCon;

import java.io.IOException;
import java.sql.SQLException;

class PopupVoteView extends VBox {
    public Stage stage;
    public Parent content;
    public Node mainRoot;
    private static int voteCand;
    @FXML
    Node root;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text cekPass_Fields;

    PopupVoteView() {
        content = ControlsHelper.initControl(this);
    }

    public static void setVote(int val){
        voteCand = val;
    }

//    @FXML
//    protected void onPassword() {
//        passwordField.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.ENTER){
//                try{
//                    onEnterBtn();
//                }
//                catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    @FXML
    protected void onYesBtn() throws SQLException {
        MySqlCon.addVote(GlobalVar.valueNIM, voteCand);
        GlobalVar.primaryStage.setScene(GlobalVar.mainScene);
        GlobalVar.primaryStage.setFullScreen(true);
        GlobalVar.primaryStage.show();
        stage.close();
    }

    @FXML
    protected void onBackBtn() {
        stage.hide();
    }
}
