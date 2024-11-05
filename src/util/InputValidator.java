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
}