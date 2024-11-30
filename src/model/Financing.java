package model;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import constants.FormattingConstants;
import util.FinancingTypesTranslated;
import util.CurrencyFormatter;

/**
 * Classe Financing que representa um financiamento imobiliário.
 * Ela calcula o pagamento mensal e o pagamento total com base no valor do imóvel, prazo do financiamento e taxa de juros anual.
 */
public abstract class Financing implements Serializable {
    private static final long serialVersionUID = 1L;
    private double propertyValue;
    private int loanTerm;
    private double interestRate;

    /**
     * Construtor para inicializar os valores do financiamento.
     *
     * @param targetPropertyValue Valor do imóvel.
     * @param loanTermInYears Prazo do financiamento em anos.
     * @param targetInterestRate Taxa de juros anual.
     */
    public Financing(double targetPropertyValue, int loanTermInYears, double targetInterestRate) {
        this.propertyValue = targetPropertyValue;
        this.loanTerm = loanTermInYears;
        this.interestRate = targetInterestRate;
    }

    /**
     * Método abstrato para calcular o pagamento MENSAL do financiamento.
     */
    public abstract double getMonthlyPayment();

    //**
    // * Método abstrato para exibir os detalhes do financiamento.
    // */
    public abstract void getFinancingDetails(int financingNumber);

    /**
     * Getters.
     */
    public double getPropertyValue() {
        return propertyValue;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Calcula o pagamento total do financiamento.
     * Total do pagamento = pagamento mensal * prazo do financiamento em anos * 12
     * @return Pagamento total.
     */
    public double getTotalPayment() {
        double monthlyPayment = this.getMonthlyPayment();
        return monthlyPayment * this.loanTerm * 12;
    }

    /**
     * Exibe os detalhes totais de todos os financiamentos fornecidos formatados em reais (R$).
     *
     * @param financings Lista de objetos de financiamento.
     */
    public static void getAllFinancingDetails(ArrayList<Financing> financings) {
        double totalPropertyValue = 0;
        double totalFinancingValue = 0;

        for (Financing financing : financings) {
            totalPropertyValue += financing.getPropertyValue();
            totalFinancingValue += financing.getTotalPayment();
        }

        System.out.printf(FormattingConstants.SEPARATOR_LINE);
        System.out.printf("Valor Total de Todos os Imóveis: %s\n", CurrencyFormatter.formatToBRL(totalPropertyValue));
        System.out.printf("Valor Total de Todos os Financiamentos: %s\n", CurrencyFormatter.formatToBRL(totalFinancingValue));
        System.out.println(FormattingConstants.SEPARATOR_LINE);
    }

    /**
     * Salva os dados de financiamento em um arquivo de texto.
     *
     * @param fileName Nome do arquivo.
     * @throws IOException Se ocorrer um erro de I/O.
     */
    public void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(this.toString());
            writer.newLine();
        }
    }

    /**
     * Lê os dados de financiamento de um arquivo de texto.
     *
     * @param fileName Nome do arquivo.
     * @return Lista de financiamentos.
     * @throws IOException Se ocorrer um erro de I/O.
     */
    public static ArrayList<Financing> readFromFile(String fileName) throws IOException {
        ArrayList<Financing> financings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("House")) {
                    double propertyValue = Double.parseDouble(parts[1]);
                    int loanTerm = Integer.parseInt(parts[2]);
                    double interestRate = Double.parseDouble(parts[3]);
                    double builtAreaSize = Double.parseDouble(parts[4]);
                    double landSize = Double.parseDouble(parts[5]);
                    financings.add(new House(propertyValue, loanTerm, interestRate, builtAreaSize, landSize));
                } else if (parts[0].equals("Apartment")) {
                    double propertyValue = Double.parseDouble(parts[1]);
                    int loanTerm = Integer.parseInt(parts[2]);
                    double interestRate = Double.parseDouble(parts[3]);
                    int garageSpaces = Integer.parseInt(parts[4]);
                    int floorNumber = Integer.parseInt(parts[5]);
                    financings.add(new Apartment(propertyValue, loanTerm, interestRate, garageSpaces, floorNumber));
                } else if (parts[0].equals("Land")) {
                    double propertyValue = Double.parseDouble(parts[1]);
                    int loanTerm = Integer.parseInt(parts[2]);
                    double interestRate = Double.parseDouble(parts[3]);
                    String zoneType = parts[4];
                    financings.add(new Land(propertyValue, loanTerm, interestRate, zoneType));
                }
            }
        }
        return financings;
    }


    @Override
    public abstract String toString();

    /**
     * Serializa a lista de financiamentos em um arquivo.
     *
     * @param financings Lista de financiamentos.
     * @param fileName Nome do arquivo.
     * @throws IOException Se ocorrer um erro de I/O.
     */
    public static void serializeFinancings(ArrayList<Financing> financings, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(financings);
        }
    }

    /**
     * Desserializa a lista de financiamentos de um arquivo.
     *
     * @param fileName Nome do arquivo.
     * @return Lista de financiamentos.
     * @throws IOException Se ocorrer um erro de I/O.
     * @throws ClassNotFoundException Se a classe não for encontrada.
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Financing> deserializeFinancings(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (ArrayList<Financing>) ois.readObject();
        }
    }
}