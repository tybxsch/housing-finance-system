package model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Classe Financing que representa um financiamento imobiliário.
 * Ela calcula o pagamento mensal e o pagamento total com base no valor do imóvel, prazo do financiamento e taxa de juros anual.
 */
public class Financing {
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
     * Getters para os atributos da classe.
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
     * Calcula o pagamento mensal do financiamento.
     * Pagamento mensal = (valor do imóvel / (prazo do financiamento em anos * 12)) * (1 + (taxa anual / 12))
     * @return Pagamento mensal.
     */
    public double getMonthlyPayment() {
        return (this.propertyValue / (this.loanTerm * 12)) * (1 + (this.interestRate / 12));
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
     * Exibe os detalhes do financiamento formatados em reais (R$).
     */
    public void getFinancingDetails(int financingNumber) {
        double totalPayment = this.getTotalPayment();
        double monthlyPayment = this.getMonthlyPayment();

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        System.out.println("=====================================");
        if (financingNumber > 0) {
            System.out.printf("         Detalhes do Financiamento %d         \n", financingNumber);
        } else {
            System.out.println("         Detalhes do Financiamento         ");
        }
        System.out.println("=====================================");
        System.out.printf("Valor do Imóvel: %s\n", currencyFormatter.format(this.propertyValue));
        System.out.printf("Prazo do Financiamento: %d anos\n", this.loanTerm);
        System.out.printf("Taxa de Juros Anual: %.2f%%\n", this.interestRate);
        System.out.printf("Pagamento Mensal: %s\n", currencyFormatter.format(monthlyPayment));
        System.out.printf("Valor Total do Financiamento: %s\n", currencyFormatter.format(totalPayment));
        System.out.println("=====================================");
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

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        System.out.println("=====================================");
        System.out.printf("Valor Total de Todos os Imóveis: %s\n", currencyFormatter.format(totalPropertyValue));
        System.out.printf("Valor Total de Todos os Financiamentos: %s\n", currencyFormatter.format(totalFinancingValue));
        System.out.println("=====================================");
    }
}