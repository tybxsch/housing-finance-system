package main;

import util.UserInterface;
import model.Financing;
import model.House;
import model.Apartment;
import model.Land;
import java.util.ArrayList;

/**
 * Classe principal que executa o programa de financiamento.
 */
public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ArrayList<Financing> financings = new ArrayList<>();

        // Solicita os dados do usuário para um financiamento
        double propertyValue = ui.setPropertyValue(1, false);
        int loanTerm = ui.setLoanTerm(1, false);
        double interestRate = ui.setInterestRate(1, false);

        // Adiciona o financiamento fornecido pelo usuário
        financings.add(new House(propertyValue, loanTerm, interestRate));

        // Adiciona os demais financiamentos com os mesmos valores de input do usuário para diferentes tipos de imóveis
        financings.add(new Apartment(propertyValue, loanTerm, interestRate));
        financings.add(new Land(propertyValue, loanTerm, interestRate));

        // Exibe os detalhes de cada financiamento individualmente
        for (int i = 0; i < financings.size(); i++) {
            Financing financing = financings.get(i);
            financing.getFinancingDetails(i + 1, financing);
        }

        // Exibe os detalhes totais de todos os financiamentos
        Financing.getAllFinancingDetails(financings);
    }
}