package model;

import java.text.NumberFormat;
import java.util.Locale;

public class Financing {
    private double propertyValue;
    private int loanTerm;
    private double interestRate;

    public Financing(double targetPropertyValue, int loanTermInYears, double targetInterestRate) {
        this.propertyValue = targetPropertyValue;
        this.loanTerm = loanTermInYears;
        this.interestRate = targetInterestRate;
    }

    public double getMonthlyPayment() {
        if (interestRate <= 0) {
            throw new IllegalArgumentException("Interest rate must be greater than zero.");
        }
        if (loanTerm <= 0) {
            throw new IllegalArgumentException("Loan term must be greater than zero.");
        }
        if (propertyValue <= 0) {
            throw new IllegalArgumentException("Property value must be greater than zero.");
        }

        return (this.propertyValue / (this.loanTerm * 12)) * (1 + (this.interestRate / 12));
    }

    public double getTotalPayment(){
        double monthlyPayment = this.getMonthlyPayment();
        return  monthlyPayment * this.loanTerm * 12;
    }

    public void getFinancingDetails() {
        double totalPayment = this.getTotalPayment();
        double monthlyPayment = this.getMonthlyPayment();

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        System.out.println("=====================================");
        System.out.println("         Detalhes do Financiamento         ");
        System.out.println("=====================================");
        System.out.printf("Valor do Imóvel: %s\n", currencyFormatter.format(this.propertyValue));
        System.out.printf("Prazo do Financiamento: %d anos\n", this.loanTerm);
        System.out.printf("Taxa de Juros Anual: %.2f%%\n", this.interestRate);
        System.out.printf("Pagamento Mensal: %s\n", currencyFormatter.format(monthlyPayment));
        System.out.printf("Valor Total do Financiamento: %s\n", currencyFormatter.format(totalPayment));
        System.out.println("=====================================");
    }
}
