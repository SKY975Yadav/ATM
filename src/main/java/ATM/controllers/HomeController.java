package ATM.controllers;
import ATM.sessions.Session;
import ATM.ui.SceneManager;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomeController{

    public Label homeHeading;
    public Button withdrawButton;
    public Button depositButton;
    public Button pinChangeButton;
    public Button checkBalanceButton;
    public Button miniStatementButton;
    public Button exitButton;

    public Scene scene;
    private Session session = Session.getInstance();

    public void withdrawAction(ActionEvent event) throws IOException {
        session.setTransactionType("withdrawal");
        SceneManager.switchScene("AmountEntry.fxml");

    }

    public void depositAction(ActionEvent actionEvent) throws IOException {
        session.setTransactionType("deposit");
        SceneManager.switchScene("AmountEntry.fxml");
    }

    public void pinChangeAction(ActionEvent actionEvent) throws IOException {
        session.setTransactionType("pinChange");
        SceneManager.switchScene("PinEntry.fxml");
    }

    public void checkBalanceAction(ActionEvent actionEvent) throws IOException {
        session.setTransactionType("checkBalance");
        SceneManager.switchScene("pinEntry.fxml");
    }

    public void miniStatementAction(ActionEvent actionEvent) throws IOException {
        session.setTransactionType("miniStatement");
        SceneManager.switchScene("pinEntry.fxml");
    }

    public void exitAction(ActionEvent event) throws IOException {
        session.setTransactionType("exit");
        SceneManager.switchScene("TransactionMessage.fxml");
    }

}
