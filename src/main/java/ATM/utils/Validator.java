package ATM.utils;
import java.math.BigDecimal;

public class Validator {

    public static boolean isValidAtmNumber(String atmNumber) {
        try {
            Long.parseLong(atmNumber);
        }catch (Exception e){
            return false;
        }
        return atmNumber.matches("\\d{12}");
    }
    public static boolean isValidAmount(String amountText){
        BigDecimal amount;
        try {
            amount = BigDecimal.valueOf(Long.parseLong(amountText));
        }catch (Exception e){
            return false;
        }
        return amount.compareTo(new BigDecimal(0)) > 0 ; //if the amount is less than or equal to zero
    }
    public static boolean isValidPin(String pinText){
        try {
            Integer.parseInt(pinText);
        }catch (Exception e){
            return false;
        }
        return pinText.length() == 4;
    }
}
