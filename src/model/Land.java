package model;

/**
 * Classe que representa um financiamento de terreno, incluindo um acréscimo de 2% sobre o valor da parcela.
 */
public class Land extends Financing {

    /**
     * Construtor para criar uma instância de Terreno.
     *
     * @param propertyValue Valor do imóvel.
     * @param loanTerm Prazo do financiamento em anos.
     * @param interestRate Taxa de juros anual.
     */
    public Land(double propertyValue, int loanTerm, double interestRate) {
        super(propertyValue, loanTerm, interestRate);
    }

    @Override
    public double getMonthlyPayment() {
        double monthlyInterestRate = this.getInterestRate() / 100 / 12;
        return (super.getPropertyValue() / (super.getLoanTerm() * 12)) * (1 + monthlyInterestRate);
    }

    /**
     * Calcula o pagamento mensal do financiamento, incluindo um acréscimo de 2%.
     *
     * @return Pagamento mensal com o acréscimo de 2%.
     */
    @Override
    public double getTotalPayment() {
        double monthlyPayment = this.getMonthlyPayment();
        return (monthlyPayment * 1.02) * ( super.getLoanTerm() * 12);
    }
}