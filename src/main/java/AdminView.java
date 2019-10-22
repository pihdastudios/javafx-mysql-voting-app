import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;

import util.mysqlcon.MySqlCon;

public class AdminView extends VBox {
    @FXML
    private TextField usernameLbl;
    @FXML
    private PasswordField passwordLbl;
    @FXML
    private TextField urlLbl;
    @FXML
    private TextField dbNameLbl;

    public AdminView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "AdminView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void setUsername() {
        MySqlCon.username = usernameLbl.getText();
    }
}
