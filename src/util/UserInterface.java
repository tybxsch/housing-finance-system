package util;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public double getPropertyValue() {
        System.out.print("Digite o valor do imóvel: ");
        return scanner.nextDouble();
    }

    public int getLoanTerm() {
        System.out.print("Digite o prazo do financiamento em anos: ");
        return scanner.nextInt();
    }

    public double getInterestRate() {
        System.out.print("Digite a taxa de juros anual: ");
        return scanner.nextDouble();
    }
}
