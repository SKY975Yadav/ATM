package ATM.services;

import ATM.models.Account;
import ATM.models.Transaction;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class DatabaseConnectionAndServices {
    private static final String url = "jdbc:mysql://localhost:3306/bank";
    private static final String name = "root";
    private static final String password = "6301722975";

    public  Optional<Account> getAccount(long atmNumber) {
        Account account = null;

        try (Connection connection = DriverManager.getConnection(url, name, password);
             PreparedStatement ps = connection.prepareStatement(
                     "SELECT Name, Account_Number, Pin, Balance FROM Accounts WHERE ATM_Number = ?")) {

            // Set ATM_Number parameter
            ps.setLong(1, atmNumber);

            // Execute query and retrieve results
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String Name = resultSet.getString("Name");
                    long Account_Number = resultSet.getLong("Account_Number");
                    int pin = resultSet.getInt("Pin");
                    BigDecimal balance = resultSet.getBigDecimal("Balance");

                    account = new Account(Name, Account_Number, atmNumber, pin, balance);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in Accessing Account"+e.getMessage());
        }
        return Optional.ofNullable(account);
    }
    public void updateBalance(Account account, BigDecimal balance){
        try (Connection connection = DriverManager.getConnection(url, name, password);
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE Accounts SET Balance = ? WHERE ATM_Number = ?")) {

            // Set parameters
            ps.setBigDecimal(1, balance);
            ps.setLong(2, account.getAtmNumber());

            // Execute update
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated <= 0) {
                System.out.println("Failed to update balance. Account not found.");

            }
        } catch (SQLException e) {
            System.out.println("Error while updating balance: " + e.getMessage());
        }
    }
    public void updatePin(Account account, int newPin){
        try (Connection connection = DriverManager.getConnection(url, name, password);
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE Accounts SET Pin = ? WHERE ATM_Number = ?")) {

            // Set parameters
            ps.setInt(1, newPin);
            ps.setLong(2, account.getAtmNumber());

            // Execute update
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("PIN updated successfully");
            } else {
                System.out.println("Failed to update PIN. Account not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating PIN: " + e.getMessage());
        }
    }

    public void uploadInTransaction(Account account, String transactionType, BigDecimal amount, BigDecimal balance){
        try (Connection connection = DriverManager.getConnection(url, name, password);
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO transactions (atm_number, transaction_type, amount, balance)\n" +
                             "VALUES (?, ?, ?, ?);")) {

            // Set parameters
            ps.setLong(1, account.getAtmNumber());
            ps.setString(2,transactionType);
            ps.setBigDecimal(3,amount);
            ps.setBigDecimal(4,balance);

            // Execute update
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated <= 0) {
                System.out.println("Error In Uploading the Transaction");

            }
        } catch (SQLException e) {
            System.out.println("Error In Uploading the Transaction : " + e.getMessage());
        }
    }
    public void uploadInTransaction(Account account, String transactionType){
        try (Connection connection = DriverManager.getConnection(url, name, password);
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO transactions (atm_number, transaction_type)\n" +
                             "VALUES (?, ?);")) {

            // Set parameters
            ps.setLong(1, account.getAtmNumber());
            ps.setString(2,transactionType);

            // Execute update
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated <= 0) {
                System.out.println("Error In Uploading the Transaction.");

            }
        } catch (SQLException e) {
            System.out.println("Error In Uploading the Transaction: " + e.getMessage());
        }
    }

    public ArrayList<Transaction> getData(Account account){

        ArrayList<Transaction> data = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, name, password);
             PreparedStatement ps = connection.prepareStatement(
                     """
                             SELECT *
                             FROM Transactions
                             WHERE atm_number = ?\s
                               AND (transaction_type = 'withdrawal' OR transaction_type = 'deposit')
                             ORDER BY transaction_id DESC;""")) {

            // Set ATM_Number parameter
            ps.setLong(1, account.getAtmNumber());

            // Execute query and retrieve results
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    long transaction_id = resultSet.getLong("transaction_id");
                    long atm_number = resultSet.getLong("atm_number");
                    Timestamp timestamp = resultSet.getTimestamp("transaction_date");
                    LocalDateTime localDateTime = timestamp.toLocalDateTime();
                    String transactionType = resultSet.getString("transaction_type");
                    BigDecimal amount = resultSet.getBigDecimal("amount");
                    BigDecimal balance = resultSet.getBigDecimal("balance");
                    data.add(new Transaction(transaction_id,atm_number,transactionType,amount,localDateTime,balance));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in Accessing Account"+e.getMessage());
        }
        return data;
    }

    public Transaction getLatestTransaction(Account account){
        Transaction transaction = null;
        try (Connection connection = DriverManager.getConnection(url, name, password);
             PreparedStatement ps = connection.prepareStatement(
                     """
                             SELECT *
                             FROM Transactions
                             WHERE atm_number = ?\s
                             ORDER BY transaction_id DESC;""")) {

            // Set ATM_Number parameter
            ps.setLong(1, account.getAtmNumber());

            // Execute query and retrieve results
            try (ResultSet resultSet = ps.executeQuery()) {
                if(resultSet.next()) {
                    long transaction_id = resultSet.getLong("transaction_id");
                    long atm_number = resultSet.getLong("atm_number");
                    Timestamp timestamp = resultSet.getTimestamp("transaction_date");
                    LocalDateTime localDateTime = timestamp.toLocalDateTime();
                    String transactionType = resultSet.getString("transaction_type");
                    BigDecimal amount = resultSet.getBigDecimal("amount");
                    BigDecimal balance = resultSet.getBigDecimal("balance");
                    transaction = new Transaction(transaction_id,atm_number,transactionType,amount,localDateTime,balance);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in Accessing Account"+e.getMessage());
        }
        return transaction;
    }
    public void uploadRating(Transaction transaction, int rating){
        try (Connection connection = DriverManager.getConnection(url, name, password);
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO ratings (transaction_id, transaction_type, atm_number, rating)\n" +
                             "VALUES (?, ?, ?, ?);")) {

            // Set parameters
            ps.setLong(1, transaction.getTransaction_id());
            ps.setString(2,transaction.getTransactionType());
            ps.setLong(3,transaction.getAtm_number());
            ps.setInt(4,rating);

            // Execute update
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated <= 0) {
                System.out.println("Error while uploading the rating");

            }
        } catch (SQLException e) {
            System.out.println("Error while uploading the rating: " + e.getMessage());
        }
    }
}
