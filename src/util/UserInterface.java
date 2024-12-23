package util;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Classe utilitária para exibição de mensagens ao usuário.
 */
public class UserInterface {

    private final Scanner scanner;

    /**
     * Construtor que inicializa o scanner.
     */
    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    /**
     * Solicita ao usuário o valor do imóvel.
     *
     * @param propertyNumber Número do imóvel.
     * @param hasMoreThanOneFinancing Indica se a numeração deve ser incluída.
     * @return O valor do imóvel.
     */
    public double setPropertyValue(int propertyNumber, boolean hasMoreThanOneFinancing) {
        String message = hasMoreThanOneFinancing ? "Digite o valor do imóvel " + propertyNumber + ": " : "Digite o valor do imóvel: ";
        return InputValidator.getPositiveDouble(message);
    }

    /**
     * Solicita ao usuário o prazo do financiamento em anos.
     *
     * @param propertyNumber Número do imóvel.
     * @param hasMoreThanOneFinancing Indica se a numeração deve ser incluída.
     * @return O prazo do financiamento em anos.
     */
    public int setLoanTerm(int propertyNumber, boolean hasMoreThanOneFinancing) {
        String message = hasMoreThanOneFinancing ? "Digite o prazo do financiamento em anos para o imóvel " + propertyNumber + ": " : "Digite o prazo do financiamento em anos: ";
        return InputValidator.getPositiveInt(message);
    }

    /**
     * Solicita ao usuário a taxa de juros anual.
     *
     * @param propertyNumber Número do imóvel.
     * @param hasMoreThanOneFinancing Indica se a numeração deve ser incluída.
     * @return A taxa de juros anual.
     */
    public double setInterestRate(int propertyNumber, boolean hasMoreThanOneFinancing) {
        return this.getReasonableInterestRate(propertyNumber, hasMoreThanOneFinancing);
    }

    /**
     * Solicita ao usuário uma taxa de juros razoável (entre 0 e 100).
     *
     * @param propertyNumber Número do imóvel.
     * @param hasMoreThanOneFinancing Indica se a numeração deve ser incluída.
     * @return Uma taxa de juros entre 0 e 100.
     */
    private double getReasonableInterestRate(int propertyNumber, boolean hasMoreThanOneFinancing) {
        double value = -1;
        String message = hasMoreThanOneFinancing ? "Digite a taxa de juros anual para o imóvel " + propertyNumber + ": " : "Digite a taxa de juros anual: ";

        while (value <= 0 || value > 100) {
            try {
                System.out.print(message);
                value = scanner.nextDouble();
                if (value <= 0 || value > 100) {
                    System.out.println("Valor inválido. Por favor, digite uma taxa de juros entre 0 e 100.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Por favor, digite um número DECIMAL.");
                scanner.next();
            }
            finally {
                scanner.nextLine();
            }
        }
        return value;
    }

    /**
     * Solicita ao usuário o número de financiamentos.
     *
     * @return O número de financiamentos.
     */
    public int setNumberOfFinancings() {
        int numberOfFinancings = 0;
        while (numberOfFinancings <= 0) {
            try {
                System.out.print("Digite o número de financiamentos: ");
                numberOfFinancings = scanner.nextInt();
                if (numberOfFinancings <= 0) {
                    System.out.println("Número inválido. Por favor, digite um número maior que zero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Por favor, digite um número inteiro.");
            }
            finally {
                scanner.nextLine();
            }
        }
        return numberOfFinancings;
    }

    /**
     * Solicita ao usuário os atributos específicos para uma casa.
     *
     * @return Um array contendo o tamanho da casa (em m²) e o número de andares.
     */
    public double[] setHouseAttributes() {
        double size = InputValidator.getPositiveDouble("Digite o tamanho da casa (em m²): ");
        double floors = InputValidator.getPositiveDouble("Digite o tamanho do terreno (em m²): ");
        return new double[]{size, floors};
    }

    /**
     * Solicita ao usuário os atributos específicos para um apartamento.
     *
     * @return Um array contendo o número do andar e o número de vagas de garagem.
     */
    public int[] setApartmentAttributes() {
        int garageSpaces = InputValidator.getPositiveInt("Digite o número de vagas de garagem: ");
        int floorNumber = InputValidator.getPositiveInt("Digite o número do andar: ");
        return new int[]{garageSpaces, floorNumber};
    }

    /**
     * Solicita ao usuário os atributos específicos para um terreno.
     *
     * @return O tipo do terreno (residencial ou comercial).
     */
    public String setLandAttributes() {
        String landType = "";
        while (true) {
            try {
                System.out.print("Digite o tipo do terreno (1 para residencial, 2 para comercial): ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    landType = "residencial";
                    break;
                } else if (choice == 2) {
                    landType = "comercial";
                    break;
                } else {
                    System.out.println("Opção inválida. Por favor, digite 1 para residencial ou 2 para comercial.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                scanner.next();
            }
        }
        return landType;
    }

}