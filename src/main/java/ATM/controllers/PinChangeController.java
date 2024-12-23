package ATM.controllers;
import ATM.models.Account;
import ATM.sessions.Session;
import ATM.ui.SceneManager;
import ATM.utils.Validator;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class PinChangeController{
    public TextField pinEntryField;
    public Label messageField;

    boolean isVerified,isNewPinEntered;
    int newPin;
    private Session session = Session.getInstance();

    public void initialize(){
        messageField.setText("Enter OLD PIN");
        pinEntryField.promptTextProperty().set("Enter OLD PIN");
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
        if (!isVerified){
            if (pinValidation() && pinVerify()){
                pinEntryField.promptTextProperty().set("Enter New PIN");
                messageField.setTextFill(Color.rgb(15, 13, 13));
                messageField.setText("Enter New PIN");
                isVerified = true;
            }
            pinEntryField.setText("");
            return;
        }

        if (!isNewPinEntered){
            if (pinValidation()) {
                isNewPinEntered = true;
                newPin = Integer.parseInt(pinEntryField.getText());
                pinEntryField.promptTextProperty().set("Confirm New PIN");
                messageField.setTextFill(Color.rgb(15, 13, 13));
                messageField.setText("Confirm New PIN");
            }
            pinEntryField.setText("");
            return;
        }

        if (!pinValidation()) return;
        if (newPin != Integer.parseInt(pinEntryField.getText())){
            messageField.setStyle("-fx-text-fill: rgb(176, 13, 13); -fx-font-size: 28;");
            messageField.setText("The PIN does not match the previous one. Please try again.");
            pinEntryField.setText("");
            return;
        }
        session.setPin(Integer.parseInt(pinEntryField.getText()));
        SceneManager.switchScene("TransactionMessage.fxml");

    }
    private boolean pinValidation(){
        if (Validator.isValidPin(pinEntryField.getText()))return true;
        else {
            pinEntryField.setText("");
            messageField.setTextFill(Color.rgb(176, 20, 20));
            messageField.setText("Invalid PIN");
            return false;
        }

    }
    private boolean pinVerify(){
        Account account = session.getAccount();
        if (account.getPin() == Integer.parseInt(pinEntryField.getText()))return true;
        else {
            pinEntryField.setText("");
            messageField.setTextFill(Color.rgb(176, 20, 20));
            messageField.setText("Incorrect OLD PIN");
            return false;
        }
    }
    public void exitAction(ActionEvent event) throws IOException {
        session.setTransactionType("exit");
        SceneManager.switchScene("TransactionMessage.fxml");
    }
}
