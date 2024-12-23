package ATM.controllers;
import ATM.sessions.Session;
import ATM.ui.SceneManager;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;

public class FinalMessageController{

    public Label endingMessage;
    protected static Session session = Session.getInstance();
    public void initialize(){
        session = null;
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> {
            // Switch to second scene after 10 seconds
            try {
                SceneManager.switchScene("WelcomeScene.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        pause.play();
    }
}
