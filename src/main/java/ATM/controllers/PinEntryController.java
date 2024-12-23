package ATM.controllers;
import ATM.models.Account;
import ATM.sessions.Session;
import ATM.ui.SceneManager;
import ATM.utils.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Objects;

public class PinEntryController {
    public TextField pinEntryField;
    public Label messageField;
    public Button enterButton;
    public Button exitButton;

    private Session session = Session.getInstance();

    public void initialize(){
        if (session.getTransactionType().equals("pinChange")){
            FXMLLoader loader = SceneManager.getLoader();
            loader.setController(new PinChangeController());
        }
    }

    public void handleNumberInput(ActionEvent event) {
        String number = ((javafx.scene.control.Button) event.getSource()).getText();
        pinEntryField.appendText(number);
    }
    public void handleBackspace(ActionEvent actionEvent) {
        String currentText = pinEntryField.getText();
        if (!currentText.isEmpty()) {
            pinEntryField.setText(currentText.substring(0, currentText.length() - 1));
        }
    }
    public void handleClear(ActionEvent actionEvent) {
        pinEntryField.setText("");
    }
    public void enterAction(ActionEvent event) throws IOException {

        if (!Validator.isValidPin(pinEntryField.getText())){
            pinEntryField.setText("");
            messageField.setTextFill(Color.rgb(176, 20, 20));
            messageField.setText("Invalid PIN");
            return;
        }
        session.setPin(Integer.parseInt(pinEntryField.getText()));

        if (!pinVerify()) return;

        if (Objects.equals(session.getTransactionType(), "miniStatement")){
            SceneManager.switchScene("ForStatement.fxml");
        }
        else SceneManager.switchScene("TransactionMessage.fxml");

    }
    private boolean pinVerify(){
        Account account = session.getAccount();
        if (account.getPin() == session.getPin()) return true;
        else {
            pinEntryField.setText("");
            messageField.setTextFill(Color.rgb(176, 20, 20));
            messageField.setText("Incorrect PIN");
            return false;
        }
    }
    public void exitAction(ActionEvent event) throws IOException {
        session.setTransactionType("exit");
        SceneManager.switchScene("TransactionMessage.fxml");
    }
}
