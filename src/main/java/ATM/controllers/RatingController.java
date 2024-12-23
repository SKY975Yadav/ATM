package ATM.controllers;
import ATM.sessions.Session;
import ATM.ui.SceneManager;
import ATM.services.ATM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

import java.io.IOException;

public class RatingController{

    @FXML
    public Label serviceMessage;
    public RadioButton excellentRadioButton;
    public RadioButton averageRadioButton;
    public Button exitButton;
    public RadioButton badRadioButton;
    public Button submitButton;
    public ToggleGroup ratings;
    public RadioButton belowAverageRadioButton;
    public Label errorMessage;
    public RadioButton goodRadioButton;

    private int rating = 0;

    ATM atm = new ATM();
    private Session session = Session.getInstance();


    public void exitAction(ActionEvent event) throws IOException {
        SceneManager.switchScene("FinalMessage.fxml");
    }

    public void submitAction(ActionEvent event) throws IOException {
        if (rating == 0){
            errorMessage.setTextFill(Color.rgb(180,12,34));
            errorMessage.setText("Select an Option before Submit");return;
        }
        atm.setRating(session.getAccount(),rating);
        SceneManager.switchScene("FinalMessage.fxml");
    }

    public void excellentPressed(ActionEvent event) { rating = 5;
    }
    public void goodPressed(ActionEvent event) { rating = 5;
    }
    public void averagePressed(ActionEvent event) { rating = 5;
    }
    public void belowAveragePressed(ActionEvent event) { rating = 5;
    }
    public void badPressed(ActionEvent event) { rating = 5;
    }
}
