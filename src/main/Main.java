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

                    // Adiciona o financiamento fornecido pelo usuário
                    financings.add(new House(propertyValue, loanTerm, interestRate, 500, 100));

                    // Adiciona os demais financiamentos com os mesmos valores de input do usuário para diferentes tipos de imóveis
                    // Valores chumbados em código conforme a instrução da atividade formativa da semana 5
                    financings.add(new Apartment(propertyValue, loanTerm, interestRate, 1, 10));
                    financings.add(new Land(propertyValue, loanTerm, interestRate, "residencial"));

                    try {
                        // Salvar dados de financiamento em um arquivo de texto
                        for (Financing financing : financings) {
                            financing.saveToFile("financings.txt");
                        }

                        // Serializar a lista de financiamentos
                        Financing.serializeFinancings(financings, "financings.ser");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try {
                        // Ler dados de financiamento do arquivo de texto
                        ArrayList<Financing> loadedFinancings = Financing.readFromFile("financings.txt");

                        // Desserializar a lista de financiamentos
                        ArrayList<Financing> deserializedFinancings = Financing.deserializeFinancings("financings.ser");
                        for (Financing financing : deserializedFinancings) {
                            System.out.println(financing);
                        }
                    } catch (IOException | ClassNotFoundException e) {
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