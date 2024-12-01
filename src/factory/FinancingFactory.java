package factory;

import model.Apartment;
import model.Financing;
import model.House;
import model.Land;
import util.UserInterface;

import java.util.Scanner;

public class FinancingFactory {
    public static Financing createFinancing(UserInterface ui) {
        double propertyValue = ui.setPropertyValue(1, false);
        int loanTerm = ui.setLoanTerm(1, false);
        double interestRate = ui.setInterestRate(1, false);

        System.out.println("Escolha o tipo de financiamento:");
        System.out.println("1. Casa");
        System.out.println("2. Apartamento");
        System.out.println("3. Terreno");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> {
                double[] houseAttributes = ui.setHouseAttributes();
                return new House(propertyValue, loanTerm, interestRate, houseAttributes[0], (int) houseAttributes[1]);
            }
            case 2 -> {
                int[] apartmentAttributes = ui.setApartmentAttributes();
                return new Apartment(propertyValue, loanTerm, interestRate, apartmentAttributes[0], apartmentAttributes[1]);
            }
            case 3 -> {
                String landType = ui.setLandAttributes();
                return new Land(propertyValue, loanTerm, interestRate, landType);
            }
            default -> {
                System.out.println("Opção inválida. Criando financiamento padrão.");
                return new House(propertyValue, loanTerm, interestRate, 500, 100);
            }
        }
    }

}

