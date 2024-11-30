package model;

import constants.FormattingConstants;
import util.CurrencyFormatter;


/**
 * Classe que representa um financiamento de apartamento, utilizando o sistema de amortização PRICE.
 */
public class Apartment extends Financing {
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

    /**
     * Retorna os detalhes do financiamento de apartamento.
     *
     * @param financingNumber Número do financiamento.
     */
    @Override
    public void getFinancingDetails(int financingNumber) {
        System.out.println(FormattingConstants.SEPARATOR_LINE);
        System.out.printf("         Detalhes do Financiamento %d - %s         \n", financingNumber, "Apartamento");
        System.out.println("Tipo: Apartamento");
        System.out.println("Valor do imóvel:" + CurrencyFormatter.formatToBRL(super.getPropertyValue()));
        System.out.println("Prazo: " + super.getLoanTerm() + " anos");
        System.out.println("Taxa de juros: " + super.getInterestRate() + "%");
        System.out.println("Número de vagas na garagem: " + this.getGarageSpaces());
        System.out.println("Número do andar: " + this.getFloorNumber());
        System.out.println("Pagamento mensal:" + CurrencyFormatter.formatToBRL(this.getMonthlyPayment()));
        System.out.printf(FormattingConstants.SEPARATOR_LINE);
        System.out.println();
    }
}