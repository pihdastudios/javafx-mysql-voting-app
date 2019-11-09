package pemilukm.teti;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
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
    @FXML
    private Label voteLabel;

    PopupVoteView() {
        content = ControlsHelper.initControl(this);
    }

    public void setVote(int val){
        voteLabel.setText("Anda memilih calon nomor urut " + val);
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
    protected void onYesBtn() throws SQLException{
        //MySqlCon.addVote(GlobalVar.valueNIM, voteCand);
        FinishedVoteView finishedVoteView = new FinishedVoteView();
        ControlsHelper.changeScene(finishedVoteView.content);
        finishedVoteView.setHotkey();
        GlobalVar.kiosk();
        GlobalVar.primaryStage.show();
        stage.close();
    }

    @FXML
    protected void onBackBtn() {
        stage.hide();
    }
}
