package ATM.controllers;
import ATM.models.Transaction;
import ATM.sessions.Session;
import ATM.ui.SceneManager;
import ATM.services.ATM;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ForStatementController {
    private Session session = Session.getInstance();
    public Label messageField;
    public Button exitButton;
    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private TableColumn<Transaction, Long> transactionIdColumn;

    @FXML
    private TableColumn<Transaction, String> transactionTypeColumn;

    @FXML
    private TableColumn<Transaction, BigDecimal> amountColumn;

    @FXML
    private TableColumn<Transaction, LocalDateTime> dateTimeColumn;

    @FXML
    private TableColumn<Transaction, BigDecimal> bankBalanceColumn;

    private final ATM atm = new ATM();
    private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws IOException {

        // Set up columns
        transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("transaction_id"));
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("localDateTime"));
        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        bankBalanceColumn.setCellValueFactory(new PropertyValueFactory<>("bankBalance"));

        // Align the text to the center for each column
        transactionIdColumn.setCellFactory(column -> {
            return new TableCell<Transaction, Long>() {
                @Override
                protected void updateItem(Long item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item != null ? item.toString() : "");
                        setStyle("-fx-alignment: CENTER;");
                    }
                }
            };
        });
        transactionTypeColumn.setCellFactory(column -> {
            return new TableCell<Transaction, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item != null ? item : "");
                        setStyle("-fx-alignment: CENTER;");
                    }
                }
            };
        });
        amountColumn.setCellFactory(column -> {
            return new TableCell<Transaction, BigDecimal>() {
                @Override
                protected void updateItem(BigDecimal item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item != null ? item.toString() : "");
                        setStyle("-fx-alignment: CENTER;");
                    }
                }
            };
        });
        dateTimeColumn.setCellFactory(column -> {
            return new TableCell<Transaction, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  // Customize this pattern
                        setText(item.format(formatter));  // Apply the format
                        setStyle("-fx-alignment: CENTER;");
                    }
                }
            };
        });
        bankBalanceColumn.setCellFactory(column -> {
            return new TableCell<Transaction, BigDecimal>() {
                @Override
                protected void updateItem(BigDecimal item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item != null ? item.toString() : "");
                        setStyle("-fx-alignment: CENTER;");
                    }
                }
            };
        });

        // Load data
        getStatement();
        PauseTransition pause = new PauseTransition(Duration.seconds(20));
        pause.setOnFinished(event -> {
            // Switch to second scene after 10 seconds
            try {
                session.setTransactionType("fairExit");
                SceneManager.switchScene("TransactionMessage.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        // Start the pause transition
        pause.play();

    }

    private void getStatement() {
        ArrayList<Transaction> data = atm.getStatement(session.getAccount());
        for (int i=0; i<10 && i<data.size();i++){
            transactions.add(data.get(i));
        }
        transactionTable.setItems(transactions);
    }

    public void exitAction(ActionEvent event) throws IOException {
        session.setTransactionType("fairExit");
        SceneManager.switchScene("TransactionMessage.fxml");
    }
}

