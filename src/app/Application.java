package app;

import factory.FinancingFactory;
import model.Financing;
import util.FinancingFileHandler;
import util.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A classe Application é responsável por gerenciar a execução do programa de financiamento.
 * Ela permite adicionar novos financiamentos e listar os financiamentos salvos.
 */
public class Application {
    private UserInterface ui;
    private ArrayList<Financing> financings;
    private String fileName;
    private String fileSerializedName;

    /**
     * Construtor da classe Application.
     * Inicializa a interface do usuário, a lista de financiamentos e os nomes dos arquivos.
     */
    public Application() {
        this.ui = new UserInterface();
        this.financings = new ArrayList<>();
        this.fileName = "financings.txt";
        this.fileSerializedName = "financings.ser";
    }

    /**
     * Método principal que executa o loop do programa.
     * Exibe o menu de opções e executa as ações correspondentes.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar novo financiamento");
            System.out.println("2. Listar financiamentos salvos");
            System.out.println("3. Sair");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> addFinancing();
                case 2 -> listFinancings();
                case 3 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    /**
     * Adiciona um novo financiamento à lista e salva nos arquivos.
     * Utiliza a fábrica de financiamentos para criar um novo financiamento.
     * Em caso de erro de E/S, exibe a pilha de erros.
     */
    private void addFinancing() {
        try {
            Financing financing = FinancingFactory.createFinancing(ui);
            financings.add(financing);
            FinancingFileHandler.saveToFile(financing, fileName);
            FinancingFileHandler.serializeFinancings(financings, fileSerializedName);
            System.out.println("Financiamento salvo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista os financiamentos salvos no arquivo.
     * Em caso de erro de E/S, exibe uma mensagem informando que não há financiamentos salvos.
     */
    private void listFinancings() {
        try {
            ArrayList<Financing> loadedFinancings = FinancingFileHandler.readFromFile(fileName);
            loadedFinancings.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Nenhum financiamento salvo.");
        }
    }
}