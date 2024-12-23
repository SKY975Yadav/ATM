package ATM.controllers;
import ATM.models.Account;
import ATM.services.DatabaseConnectionAndServices;
import ATM.sessions.Session;
import ATM.ui.SceneManager;
import ATM.utils.Validator;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Optional;

public class WelcomeController {

    public TextField atmNumEntryField;
    public Label errorMessage;
    public ImageView img;
    public Button atmNumSubmitButton;
    public Label welcomeText;
    protected static Session session = Session.getInstance();

    DatabaseConnectionAndServices databaseConnectionAndServices = new DatabaseConnectionAndServices();

    public void SubmitOfAtmNumber(ActionEvent event) throws IOException {

        String atmNumString = atmNumEntryField.getText();

        if (!Validator.isValidAtmNumber(atmNumString)){
            errorMessage.setText("Enter Valid 12 Digit Atm Number");
            return;
        }

        Optional<Account> accountOpt = databaseConnectionAndServices.getAccount(Long.parseLong(atmNumString));
        if (accountOpt.isEmpty()) {
            errorMessage.setText("Account Not Found");
            return;
        }

        session.setAccountDetails(accountOpt.get());
        SceneManager.switchScene("Home.fxml");
    }

}
