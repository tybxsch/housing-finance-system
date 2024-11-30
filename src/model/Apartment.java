package model;

import constants.FormattingConstants;
import util.CurrencyFormatter;

import java.io.Serializable;


/**
 * Classe que representa um financiamento de apartamento, utilizando o sistema de amortização PRICE.
 */
public class Apartment extends Financing implements Serializable {
    private static final long serialVersionUID = 1L;
    private int garageSpaces;
    private int floorNumber;

    /**
     * Construtor para criar uma instância de Apartamento.
     *
     * @param propertyValue Valor do imóvel.
     * @param loanTerm Prazo do financiamento em anos.
     * @param interestRate Taxa de juros anual.
     * @param garageSpaces Número de vagas da garagem.
     * @param floorNumber Número do andar.
     */
    public Apartment(double propertyValue, int loanTerm, double interestRate, int garageSpaces, int floorNumber) {
        super(propertyValue, loanTerm, interestRate);
        this.garageSpaces = garageSpaces;
        this.floorNumber = floorNumber;
    }

    /**
     * Getters e setters.
     */
    public int getGarageSpaces() {
        return garageSpaces;
    }

    public void setGarageSpaces(int garageSpaces) {
        this.garageSpaces = garageSpaces;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    /**
     * Calcula o pagamento mensal do financiamento utilizando o sistema de amortização PRICE.
     *
     * @return Pagamento mensal.
     */
    @Override
    public double getMonthlyPayment() {
        // Juros mensal
        double monthlyInterestRate = super.getInterestRate() / 100 / 12;

        // Total de meses do financiamento
        int months = this.getLoanTerm() * 12;

        // Aplica sistema PRICE
        double numerator = super.getPropertyValue() * Math.pow(1 + monthlyInterestRate, months) * monthlyInterestRate;
        double denominator = Math.pow(1 + monthlyInterestRate, months) - 1;

        return numerator / denominator;
    }

    @Override
    public String toString() {
        return String.format(
                FormattingConstants.SEPARATOR_LINE +
                        "         Detalhes do Financiamento - Apartamento         \n" +
                "Tipo: Apartamento\n" +
                        "Valor do imóvel: %s\n" +
                        "Prazo: %d anos\n" +
                        "Taxa de juros: %.2f%%\n" +
                        "Número de vagas na garagem: %d\n" +
                        "Número do andar: %d\n" +
                        "Pagamento mensal: %s\n" +
                        "Pagamento total: %s\n" +
                        FormattingConstants.SEPARATOR_LINE,
                CurrencyFormatter.formatToBRL(getPropertyValue()),
                getLoanTerm(),
                getInterestRate(),
                garageSpaces,
                floorNumber,
                CurrencyFormatter.formatToBRL(getMonthlyPayment()),
                CurrencyFormatter.formatToBRL(getTotalPayment())
        );
    }
}