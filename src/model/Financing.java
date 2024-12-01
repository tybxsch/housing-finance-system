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
}