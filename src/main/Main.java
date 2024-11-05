package main;

import util.UserInterface;
import model.Financing;
import java.util.ArrayList;

/**
 * Classe principal que executa o programa de financiamento.
 */
public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ArrayList<Financing> financings = new ArrayList<>();

        int numberOfFinancings = ui.setNumberOfFinancings();
        boolean hasMoreThanOneFinancing = numberOfFinancings > 1;

        for (int i = 0; i < numberOfFinancings; i++) {
            int propertyNumber = i + 1;
            double propertyValue = ui.setPropertyValue(propertyNumber, hasMoreThanOneFinancing);
            int loanTerm = ui.setLoanTerm(propertyNumber, hasMoreThanOneFinancing);
            double interestRate = ui.setInterestRate(propertyNumber, hasMoreThanOneFinancing);

            Financing financing = new Financing(propertyValue, loanTerm, interestRate);
            financings.add(financing);
        }

        Financing.getAllFinancingDetails(financings);
    }
}