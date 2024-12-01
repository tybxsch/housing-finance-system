package main;

import util.UserInterface;
import model.Financing;
import model.House;
import model.Apartment;
import model.Land;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principal que executa o programa de financiamento.
 */
public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ArrayList<Financing> financings = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar novo financiamento");
            System.out.println("2. Listar financiamentos salvos");
            System.out.println("3. Sair");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Solicita os dados do usuário para um financiamento
                    double propertyValue = ui.setPropertyValue(1, false);
                    int loanTerm = ui.setLoanTerm(1, false);
                    double interestRate = ui.setInterestRate(1, false);

                    // Cria novos financiamentos
                    Financing house = new House(propertyValue, loanTerm, interestRate, 500, 100);
                    Financing apartment = new Apartment(propertyValue, loanTerm, interestRate, 1, 10);
                    Financing land = new Land(propertyValue, loanTerm, interestRate, "residencial");

                    // Adiciona os novos financiamentos
                    financings.add(house);
                    financings.add(apartment);
                    financings.add(land);

                    try {
                        // Salva os novos financiamentos no arquivo
                        house.saveToFile("financings.txt");
                        apartment.saveToFile("financings.txt");
                        land.saveToFile("financings.txt");

                        // Serializa toda a lista de financiamentos
                        Financing.serializeFinancings(financings, "financings.ser");

                        System.out.println("Financiamentos salvos com sucesso.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try {
                        // Ler financiamentos do arquivo
                        ArrayList<Financing> loadedFinancings = Financing.readFromFile("financings.txt");

                        // Exibir os financiamentos lidos
                        if (loadedFinancings.isEmpty()) {
                            System.out.println("Nenhum financiamento salvo.");
                        } else {
                            for (Financing financing : loadedFinancings) {
                                System.out.println(financing);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
