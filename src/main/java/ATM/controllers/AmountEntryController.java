package ATM.controllers;
import ATM.sessions.Session;
import ATM.ui.SceneManager;
import ATM.utils.Validator;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

public class AmountEntryController  {

    public TextField amountEntryField;
    public Label messageField;
    public Button enterButton;
    public Button exitButton;

    private Session session = Session.getInstance();
    public void initialise(){
        // Add key event handler
        amountEntryField.setOnKeyPressed(event -> {
            if (Objects.requireNonNull(event.getCode()) == KeyCode.ENTER) {
                enterButton.fire(); // Trigger button click on Enter
            }
        });
    }
    public void handleNumberInput(ActionEvent event) {
        String number = ((javafx.scene.control.Button) event.getSource()).getText();
        amountEntryField.appendText(number);
    }

    public void handleBackspace(ActionEvent actionEvent) {
        String currentText = amountEntryField.getText();
        if (!currentText.isEmpty()) {
            amountEntryField.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    public void handleClear(ActionEvent actionEvent) {
        amountEntryField.setText("");
    }

    public void enterAction(ActionEvent event) throws IOException {

        if (!Validator.isValidAmount(amountEntryField.getText())){
            amountEntryField.setText("");
            messageField.setTextFill(Color.rgb(176, 20, 20));
            messageField.setText("Enter Valid Amount");
            return;
        }

        session.setAmount(new BigDecimal(Long.parseLong(amountEntryField.getText())));
        SceneManager.switchScene("PinEntry.fxml");

    }

    public void exitAction(ActionEvent event) throws IOException {
        session.setTransactionType("exit");
        SceneManager.switchScene("TransactionMessage.fxml");
    }
}
