package util;

import constants.FormattingConstants;
import model.Financing;
import model.House;
import model.Apartment;
import model.Land;

import java.io.*;
import java.util.ArrayList;

/**
 * Classe utilitária para manipulação de arquivos relacionados a objetos de financiamento.
 * Suporta salvar, ler, serializar e desserializar dados de financiamento.
 */
public class FinancingFileHandler {

    /**
     * Salva um objeto de financiamento em um arquivo de texto.
     *
     * @param financing O objeto de financiamento a ser salvo.
     * @param fileName  O nome do arquivo onde o objeto será salvo.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    public static void saveToFile(Financing financing, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(financing.toString());
            writer.newLine();
        }
    }

    /**
     * Lê objetos de financiamento de um arquivo de texto e os retorna como uma lista.
     *
     * @param fileName O nome do arquivo para leitura.
     * @return Uma lista de objetos de financiamento lidos do arquivo.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    public static ArrayList<Financing> readFromFile(String fileName) throws IOException {
        ArrayList<Financing> financings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder block = new StringBuilder();
            boolean isInsideBlock = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith(FormattingConstants.SEPARATOR_LINE)) {
                    if (isInsideBlock) {
                        financings.add(parseFinancingBlock(block.toString()));
                        block.setLength(0);
                    }
                    isInsideBlock = !isInsideBlock;
                } else if (isInsideBlock) {
                    block.append(line).append("\n");
                }
            }

            if (block.length() > 0) {
                financings.add(parseFinancingBlock(block.toString()));
            }
        }
        return financings;
    }

    /**
     * Converte um bloco de texto em um objeto de financiamento.
     *
     * @param block O bloco de texto a ser convertido.
     * @return Um objeto de financiamento criado a partir do bloco de texto.
     * @throws IllegalArgumentException Se o bloco contiver um tipo desconhecido.
     */
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

    /**
     * Extrai o double de um array de linhas com base nos identificadores de items dos blocos de financiamento.
     *
     * @param lines O array de linhas.
     * @param prefix O prefixo do texto a ser buscado.
     * @return O valor double extraído ou 0 se não encontrado.
     */
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


    /**
     * Extrai o inteiro de um array de linhas com base nos identificadores de items dos blocos de financiamento.
     *
     * @param lines O array de linhas.
     * @param prefix O prefixo do texto a ser buscado.
     * @return O valor inteiro extraído ou 0 se não encontrado.
     */
    private static int extractInt(String[] lines, String prefix) {
        for (String line : lines) {
            if (line.contains(prefix)) {
                String value = line.replace(prefix, "").replaceAll("[^0-9]", "");
                return Integer.parseInt(value);
            }
        }
        return 0;
    }

    /**
     * Extrai a string de um array de linhas com base nos identificadores de items dos blocos de financiamento.
     *
     * @param lines O array de linhas.
     * @param prefix O prefixo do texto a ser buscado.
     * @return O valor string extraído ou vazio se não encontrado.
     */
    private static String extractString(String[] lines, String prefix) {
        for (String line : lines) {
            if (line.contains(prefix)) {
                return line.replace(prefix, "").trim();
            }
        }
        return "";
    }

    /**
     * Serializa uma lista de objetos de financiamento para um arquivo binário.
     *
     * @param financings A lista de objetos de financiamento a ser serializada.
     * @param fileName   O nome do arquivo onde os dados serão salvos.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    public static void serializeFinancings(ArrayList<Financing> financings, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(financings);
        }
    }

    /**
     * Desserializa uma lista de objetos de financiamento de um arquivo binário.
     *
     * @param fileName O nome do arquivo de onde os dados serão lidos.
     * @return Uma lista de objetos de financiamento desserializados.
     * @throws IOException            Se ocorrer um erro de entrada/saída.
     * @throws ClassNotFoundException Se a classe não for encontrada durante a desserialização.
     */
    public static ArrayList<Financing> deserializeFinancings(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (ArrayList<Financing>) ois.readObject();
        }
    }
}