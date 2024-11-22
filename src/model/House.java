package model;

/**
 * Classe que representa um financiamento de casa, incluindo um valor adicional de seguro obrigatório.
 */
public class House extends Financing {
    private static final double MANDATORY_INSURANCE = 80.0;

    /**
     * Construtor para criar uma instância de House.
     *
     * @param propertyValue Valor do imóvel.
     * @param loanTerm Prazo do financiamento em anos.
     * @param interestRate Taxa de juros anual.
     */
    public House(double propertyValue, int loanTerm, double interestRate) {
        super(propertyValue, loanTerm, interestRate);
    }

    /**
     * Calcula o pagamento mensal do financiamento, incluindo o valor do seguro obrigatório.
     *
     * @return Pagamento mensal com o seguro obrigatório.
     */
    @Override
    public double getMonthlyPayment() {
        double monthlyPayment = super.getMonthlyPayment();
        return monthlyPayment + MANDATORY_INSURANCE;
    }
}