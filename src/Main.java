import util.UserInterface;
import model.Financing;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();

        double propertyValue = ui.getPropertyValue();
        int loanTerm = ui.getLoanTerm();
        double interestRate = ui.getInterestRate();

        Financing financing = new Financing(propertyValue, loanTerm, interestRate);

        financing.getFinancingDetails();
    }
}
