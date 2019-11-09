package pemilukm.teti;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GlobalVar {
	public static Stage primaryStage;
    public static Scene mainScene;
    public static String valueNIM;
    public static String textNIM;
    public static boolean connActive;
    public static String AESKey = "<]pemilu!TETI19*";
    public static String passAdmin = "semangat";
    public static String passMain = "19lagi";

    public static void kiosk(){
        primaryStage.setAlwaysOnTop(true);
        //primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint(null);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
            }
        });
    }
}