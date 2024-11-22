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

        // Adiciona os demais financiamentos diretamente no código
        financings.add(new House(300000, 20, 5.0));
        financings.add(new Apartment(200000, 15, 4.5));
        financings.add(new Land(150000, 10, 6.0));

        // Exibe os detalhes de cada financiamento individualmente
        for (int i = 0; i < financings.size(); i++) {
            Financing financing = financings.get(i);
            financing.getFinancingDetails(i + 1);
        }

        // Exibe os detalhes totais de todos os financiamentos
        Financing.getAllFinancingDetails(financings);
    }
}