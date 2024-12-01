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
            StringBuilder block = new StringBuilder();
            boolean isInsideBlock = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("=====================================================")) {
                    if (isInsideBlock) {
                        // Finaliza o bloco atual e parseia
                        financings.add(parseFinancingBlock(block.toString()));
                        block.setLength(0); // Reseta o bloco
                    }
                    isInsideBlock = !isInsideBlock; // Alterna entre início/fim de bloco
                } else if (isInsideBlock) {
                    block.append(line).append("\n");
                }
            }

            // Processa o último bloco, se houver
            if (block.length() > 0) {
                financings.add(parseFinancingBlock(block.toString()));
            }
        }
        return financings;
    }

    private static Financing parseFinancingBlock(String block) {
        String[] lines = block.split("\n");
        String type = null;
        double propertyValue = 0;
        int loanTerm = 0;
        double interestRate = 0;

        for (String line : lines) {
            if (line.contains("Tipo: Casa")) {
                type = "House";
            } else if (line.contains("Tipo: Apartamento")) {
                type = "Apartment";
            } else if (line.contains("Tipo de imóvel: Terreno")) {
                type = "Land";
            } else if (line.contains("Valor do imóvel:")) {
                propertyValue = extractDouble(lines, "Valor do imóvel:");
            } else if (line.contains("Prazo:")) {
                loanTerm = extractInt(lines, "Prazo:");
            } else if (line.contains("Taxa de juros")) {
                interestRate = extractDouble(lines, "Taxa de juros");
            }
        }

        switch (type) {
            case "House":
                double houseArea = extractDouble(lines, "Tamanho da área construída:");
                double garageSize = extractDouble(lines, "Tamanho do terreno:");
                return new House(propertyValue, loanTerm, interestRate, houseArea, garageSize);

            case "Apartment":
                int garageSpaces = extractInt(lines, "Número de vagas na garagem:");
                int floor = extractInt(lines, "Número do andar:");
                return new Apartment(propertyValue, loanTerm, interestRate, garageSpaces, floor);

            case "Land":
                String zoning = extractString(lines, "Tipo de zona:");
                return new Land(propertyValue, loanTerm, interestRate, zoning);

            default:
                throw new IllegalArgumentException("Tipo desconhecido no bloco: " + block);
        }
    }



    private static double extractDouble(String[] lines, String prefix) {
        for (String line : lines) {
            if (line.contains(prefix)) {
                String value = line.replace(prefix, "")
                        .replaceAll("[^0-9,.-]", "")
                        .replace(".", "")
                        .replace(",", ".");
                return Double.parseDouble(value);
            }
        }
        return 0;
    }

    private static int extractInt(String[] lines, String prefix) {
        for (String line : lines) {
            if (line.contains(prefix)) {
                String value = line.replace(prefix, "").replaceAll("[^0-9]", "");
                return Integer.parseInt(value);
            }
        }
        return 0;
    }

    private static String extractString(String[] lines, String prefix) {
        for (String line : lines) {
            if (line.contains(prefix)) {
                return line.replace(prefix, "").trim();
            }
        }
        return "";
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