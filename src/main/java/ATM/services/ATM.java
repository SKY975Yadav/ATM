package ATM.services;

import ATM.models.Account;
import ATM.models.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;


public class ATM {
    DatabaseConnectionAndServices databaseConnectionAndServices = new DatabaseConnectionAndServices();

    public boolean withdrawal(BigDecimal amount, Account account) {
        if (amount.compareTo(account.getBankBalance()) <= 0) {
            account.setBankBalance(account.getBankBalance().subtract(amount));
            databaseConnectionAndServices.updateBalance(account, account.getBankBalance()); // Update in DB
            databaseConnectionAndServices.uploadInTransaction(account,"withdrawal",amount,account.getBankBalance());
            return true;
        } else {
            return false;
        }
    }

    public void deposit(BigDecimal amount, Account account) {
        account.setBankBalance(account.getBankBalance().add(amount));
        databaseConnectionAndServices.updateBalance(account, account.getBankBalance()); // Update in DB
        databaseConnectionAndServices.uploadInTransaction(account,"deposit",amount,account.getBankBalance());
    }

    public void changePin(Account account, int newPin1) {
        account.setPin(newPin1);
        databaseConnectionAndServices.updatePin(account, newPin1); // Update in DB
        databaseConnectionAndServices.uploadInTransaction(account,"PIN change");
    }

    public BigDecimal getBalance(Account account){
        databaseConnectionAndServices.uploadInTransaction(account,"Balance Check");
        return account.getBankBalance();
    }

    public ArrayList<Transaction> getStatement(Account account){
        databaseConnectionAndServices.uploadInTransaction(account,"Mini Statement");
        return databaseConnectionAndServices.getData(account);
    }

    public void setRating(Account account, int rating){
        Transaction transaction = databaseConnectionAndServices.getLatestTransaction(account);
        databaseConnectionAndServices.uploadRating(transaction,rating);
    }
}

