package model;

import model.exceptions.IncreaseGreaterThanInterestException;

/**
 * Classe que representa um financiamento de casa, incluindo um valor adicional de seguro obrigatório.
 */
public class House extends Financing {
    private static final double MANDATORY_INSURANCE = 80.0;
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
    }

    /**
     * Getters e setters para os novos atributos.
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

    /**
     * Verifica se a taxa de juros é válida.
     *
     * @param interestRate A taxa de juros anual.
     * @return true se a taxa de juros for válida.
     * @throws IncreaseGreaterThanInterestException se o acréscimo do valor de pagamento mensal for maior que 80.
     */
    private boolean isInterestRateValid(double interestRate) throws IncreaseGreaterThanInterestException {
        double monthlyIncrease = (super.getPropertyValue() / (super.getLoanTerm() * 12)) * (interestRate / 100 / 12);

        if (monthlyIncrease > MANDATORY_INSURANCE) {
            throw new IncreaseGreaterThanInterestException("O acréscimo do valor de pagamento mensal não pode ser maior que 80.");
        }

        return true;
    }

    /**
     * Calcula o pagamento mensal do financiamento.
     *
     * @return O valor do pagamento mensal.
     */
    @Override
    public double getMonthlyPayment() {
        double monthlyInterestRate = super.getInterestRate() / 100 / 12;
        double baseMonthlyPayment = (super.getPropertyValue() / (super.getLoanTerm() * 12)) * (1 + monthlyInterestRate);

        try {
            isInterestRateValid(monthlyInterestRate);
        } catch (IncreaseGreaterThanInterestException e) {

        }

        return baseMonthlyPayment + MANDATORY_INSURANCE;
    }
}