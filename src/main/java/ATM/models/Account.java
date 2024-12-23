package ATM.models;
import java.math.BigDecimal;
public class Account {
    private final String name;
    private final long accountNumber;
    private final long atmNumber;
    private BigDecimal bankBalance;
    private int pin;

    public Account(String name, long accountNumber, long atmNumber, int pin, BigDecimal bankBalance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.atmNumber = atmNumber;
        this.pin = pin;
        this.bankBalance = bankBalance;
    }

    public BigDecimal getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(BigDecimal bankBalance) {
        this.bankBalance = bankBalance;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public long getAtmNumber() {
        return atmNumber;
    }
}
