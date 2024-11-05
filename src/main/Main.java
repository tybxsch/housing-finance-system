package main;

import util.UserInterface;
import model.Financing;
import java.util.ArrayList;

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

        // Exibe os detalhes de cada financiamento individualmente
        for (int i = 0; i < financings.size(); i++) {
            Financing financing = financings.get(i);
            financing.getFinancingDetails(hasMoreThanOneFinancing ? i + 1 : 0);
        }

        // Exibe os detalhes totais de todos os financiamentos
        Financing.getAllFinancingDetails(financings);
    }
}