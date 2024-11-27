package model;

/**
 * Classe que representa um financiamento de apartamento, utilizando o sistema de amortização PRICE.
 */
public class Apartment extends Financing {

    /**
     * Construtor para criar uma instância de Apartamento.
     *
     * @param propertyValue Valor do imóvel.
     * @param loanTerm Prazo do financiamento em anos.
     * @param interestRate Taxa de juros anual.
     */
    public Apartment(double propertyValue, int loanTerm, double interestRate) {
        super(propertyValue, loanTerm, interestRate);
    }

    /**
     * Calcula o pagamento mensal do financiamento utilizando o sistema de amortização PRICE.
     *
     * @return Pagamento mensal.
     */
    @Override
    public double getMonthlyPayment() {
        double monthlyInterestRate = super.getInterestRate() / 100 / 2;
        int months = this.getLoanTerm() * 12;
        double numerator = super.getPropertyValue() * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, months);
        double denominator = Math.pow(1 + monthlyInterestRate, months) - 1;
        return numerator / denominator;
    }

    @Override
    public double getTotalPayment() {
        double monthlyPayment = getMonthlyPayment();
        return getMonthlyPayment() * super.getLoanTerm() * 12;
    }
}