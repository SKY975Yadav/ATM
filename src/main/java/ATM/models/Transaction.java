package ATM.models;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final long transaction_id;
    private final long atm_number;
    private final String transactionType;
    private final BigDecimal amount;
    private final LocalDateTime localDateTime;
    private final BigDecimal bankBalance;

    public Transaction(long transaction_id, long atm_number, String transactionType, BigDecimal amount, LocalDateTime localDateTime, BigDecimal bankBalance) {
        this.transaction_id = transaction_id;
        this.atm_number = atm_number;
        this.transactionType = transactionType;
        this.amount = amount;
        this.localDateTime = localDateTime;
        this.bankBalance = bankBalance;
    }

    public long getTransaction_id() {
        return transaction_id;
    }

    public long getAtm_number() {
        return atm_number;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public BigDecimal getBankBalance() {
        return bankBalance;
    }

}
