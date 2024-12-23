package ATM.ui;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static Stage stage;
    private static FXMLLoader loader;
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void switchScene(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/"+fxmlPath));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }
    public static FXMLLoader getLoader(){
        return loader;
    }

}
