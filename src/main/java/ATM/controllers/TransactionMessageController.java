package ATM.controllers;
import ATM.sessions.Session;
import ATM.ui.SceneManager;
import ATM.services.ATM;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.math.BigDecimal;

public class TransactionMessageController extends WelcomeController {

    public Button exitButton;
    public Button rateUsButton;
    @FXML
    public Label transactionMessage;
    @FXML
    public Label messageForCheckBalance;
    @FXML
    public Button checkBalanceButton;

    ATM atm = new ATM();
    private boolean isClicked;
    private boolean checkedBalance;
    Session session = Session.getInstance();

    public void initialize(){
        switch (session.getTransactionType()){
            case "withdrawal" : withdrawalProcess();break;
            case "deposit" : depositProcess();break;
            case "checkBalance" : checkBalance();break;
            case "pinChange" : pinChange();break;
            case "exit" : exit();break;
            case "fairExit" : fairExit();break;
            default: System.out.println("Something is wrong in TransactionType");
        }

        // Visible only when we modify the bank balance
        if (isBalanceModified()) {
            messageForCheckBalance.setVisible(true);
            checkBalanceButton.setVisible(true);
        }
        if (checkedBalance){
            messageForCheckBalance.setVisible(false);
            checkBalanceButton.setVisible(false);
        }

        // Create a PauseTransition for 5 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(10));
        pause.setOnFinished(event -> {
            if (isClicked) return;
            // Switch to second scene after 10 seconds
            try {
                SceneManager.switchScene("FinalMessage.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Start the pause transition
        pause.play();
    }

    private void withdrawalProcess(){
        if (atm.withdrawal(session.getAmount(),session.getAccount())){
            forSuccessFull();
            transactionMessage.setText("Withdraw Successful. Please collect your Cash!");
        }
        else {
            forUnSuccessFull();
            transactionMessage.setText("Insufficient Amount");
        }
    }
    private void depositProcess(){
        atm.deposit(session.getAmount(),session.getAccount());
        forSuccessFull();
        transactionMessage.setText("Deposit Successful");
    }
    private void checkBalance(){
        BigDecimal balance = atm.getBalance(session.getAccount());
        transactionMessage.setTextFill(Color.rgb(4,10,70));
        transactionMessage.setText("Bank Balance : "+balance);
    }
    private void pinChange(){
        atm.changePin(session.getAccount(),session.getPin());
        forSuccessFull();
        transactionMessage.setText("PIN Changed Successfully");
    }
    private void exit(){
        forUnSuccessFull();
        transactionMessage.setText("TRANSACTION FAILED");
    }
    private void fairExit(){
        forSuccessFull();
        transactionMessage.setText("Statement Request Successful");
    }


    private boolean isBalanceModified(){
        return session.getTransactionType().equals("deposit") || session.getTransactionType().equals("withdrawal");
    }

    public void checkBalanceAction(ActionEvent actionEvent) throws IOException {
        isClicked = true;
        checkedBalance  = true;
        session.setTransactionType("checkBalance");
        initialize();
    }

    private void forSuccessFull(){
        transactionMessage.setTextFill(Color.rgb(4,20,78));
    }
    private void forUnSuccessFull(){
        transactionMessage.setTextFill(Color.rgb(170,20,10));
    }

    public void exitAction(ActionEvent event) throws IOException {
        isClicked = true;
        SceneManager.switchScene("FinalMessage.fxml");
    }
    public void rateUsAction(ActionEvent event) throws IOException{
        isClicked = true;
        SceneManager.switchScene("Rating.fxml");
    }
}
