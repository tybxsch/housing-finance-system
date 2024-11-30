package model;

import model.exceptions.IncreaseGreaterThanInterestException;
import constants.FormattingConstants;
import util.CurrencyFormatter;

/**
 * Classe que representa um financiamento de casa, incluindo um valor adicional de seguro obrigatório.
 */
public class House extends Financing {
    private static final double DEFAULT_INCREASE = 80;
    private double increase;
    private double builtAreaSize;
    private double landSize;

    /**
     * Construtor para criar uma instância de House.
     *
     * @param propertyValue Valor do imóvel.
     * @param loanTerm Prazo do financiamento em anos.
     * @param interestRate Taxa de juros anual.
     * @param builtAreaSize Tamanho da área construída.
     * @param landSize Tamanho do terreno.
     */
    public House(double propertyValue, int loanTerm, double interestRate, double builtAreaSize, double landSize) {
        super(propertyValue, loanTerm, interestRate);
        this.builtAreaSize = builtAreaSize;
        this.landSize = landSize;
        this.increase = DEFAULT_INCREASE;
    }

    /**
     * Getters e setters.
     */
    public double getBuiltAreaSize() {
        return builtAreaSize;
    }

    public void setBuiltAreaSize(double builtAreaSize) {
        this.builtAreaSize = builtAreaSize;
    }

    public double getLandSize() {
        return landSize;
    }

    public void setLandSize(double landSize) {
        this.landSize = landSize;
    }

    public double getIncrease() {
        return increase;
    }

    /**
     * Verifica se o valor do acréscimo é maior que o valor dos juros.
     *
     * @param monthlyIncrease O valor dos juros.
     * @throws IncreaseGreaterThanInterestException se o valor do acréscimo for maior que o valor dos juros.
     */
    private void isValidateIncrease(double monthlyIncrease) throws IncreaseGreaterThanInterestException {
        if (increase > monthlyIncrease) {
            throw new IncreaseGreaterThanInterestException(String.format(
                    "O valor do acréscimo de %s é maior que o valor dos juros de %s.",
                    CurrencyFormatter.formatToBRL(increase),
                    CurrencyFormatter.formatToBRL(monthlyIncrease)
            ));
        }
    }

    /**
     * Calcula o pagamento mensal do financiamento.
     *
     * @return O valor do pagamento mensal.
     */
    @Override
    public double getMonthlyPayment() {
        double monthlyInterestRate = super.getInterestRate() / 100 / 12;
        double baseMonthlyPayment = super.getPropertyValue() / (super.getLoanTerm() * 12);
        double monthlyPaymentWithInterest = baseMonthlyPayment * (1 + monthlyInterestRate);
        double monthlyIncrease = monthlyPaymentWithInterest - baseMonthlyPayment;

        try {
            isValidateIncrease(monthlyIncrease);
        } catch (IncreaseGreaterThanInterestException e) {
            System.out.println("Erro: " + e.getMessage());
            increase = monthlyIncrease;
            System.out.println("Ajustando o acréscimo para ser igual ao valor dos juros: " + CurrencyFormatter.formatToBRL(monthlyIncrease));
        }

        return baseMonthlyPayment + monthlyIncrease + increase;
    }

    /**
     * Retorna os detalhes do financiamento de casa.
     *
     * @param financingNumber Número do financiamento.
     */
    @Override
    public void getFinancingDetails(int financingNumber) {
        System.out.println(FormattingConstants.SEPARATOR_LINE);
        System.out.printf("         Detalhes do Financiamento %d - %s         \n", financingNumber, "Casa");
        System.out.println("Tipo: Casa");
        System.out.println("Valor do imóvel: " + CurrencyFormatter.formatToBRL(super.getPropertyValue()));
        System.out.println("Prazo: " + super.getLoanTerm() + " anos");
        System.out.println("Taxa de juros: " + super.getInterestRate() + "%");
        System.out.println("Tamanho da área construída: " + this.getBuiltAreaSize() + " m²");
        System.out.println("Tamanho do terreno: " + this.getLandSize() + " m²");
        System.out.println("Pagamento mensal: " + CurrencyFormatter.formatToBRL(this.getMonthlyPayment()));
        System.out.printf(FormattingConstants.SEPARATOR_LINE);
        System.out.println();
    }
}
