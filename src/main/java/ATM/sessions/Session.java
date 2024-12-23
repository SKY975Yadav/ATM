package ATM.sessions;
import ATM.models.Account;

import java.math.BigDecimal;

public class Session {
    private static Session instance;
    private Account account;
    private BigDecimal amount;
    private Integer pin;
    private String transactionType;

    private Session() {} // Private constructor to restrict instantiation

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccountDetails(Account account) {
        this.account = account;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
