package util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe utilitária para validação de tipos de dados.
 */
public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Solicita ao usuário um número decimal positivo.
     *
     * @param message Mensagem a ser exibida ao usuário.
     * @return Um número decimal positivo.
     */
    public static double getPositiveDouble(String message) {
        double value = -1;
        while (value <= 0) {
            try {
                System.out.print(message);
                value = scanner.nextDouble();
                if (value <= 0) {
                    System.out.println("Valor inválido. Por favor, digite um número POSITIVO.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Por favor, digite um número DECIMAL.");
                scanner.next();
            }
        }
        return value;
    }

    /**
     * Solicita ao usuário um número inteiro positivo.
     *
     * @param message Mensagem a ser exibida ao usuário.
     * @return Um número inteiro positivo.
     */
    public static int getPositiveInt(String message) {
        int value = -1;
        while (value <= 0) {
            try {
                System.out.print(message);
                value = scanner.nextInt();
                if (value <= 0) {
                    System.out.println("Valor inválido. Por favor, digite um número POSITIVO.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Por favor, insira um número INTEIRO.");
                scanner.next();
            }
        }
        return value;
    }

    /**
     * Solicita ao usuário uma taxa de juros razoável (entre 0 e 100).
     *
     * @param message Mensagem a ser exibida ao usuário.
     * @return Uma taxa de juros entre 0 e 100.
     */
    public static double getReasonableInterestRate(String message) {
        double value = -1;
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
        }
        return value;
    }
}