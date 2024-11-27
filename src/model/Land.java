package model;

/**
 * Classe que representa um financiamento de terreno, incluindo um acréscimo de 2% sobre o valor da parcela.
 */
public class Land extends Financing {
    private String zoneType;

    /**
     * Construtor para criar uma instância de Terreno.
     *
     * @param propertyValue Valor do imóvel.
     * @param loanTerm Prazo do financiamento em anos.
     * @param interestRate Taxa de juros anual.
     * @param zoneType Tipo de zona (residencial ou comercial).
     */
    public Land(double propertyValue, int loanTerm, double interestRate, String zoneType) {
        super(propertyValue, loanTerm, interestRate);
        this.zoneType = zoneType;
    }

    /**
     * Getters e setters para o tipo de zona.
     */
    public String getZoneType() {
        return zoneType;
    }

    public void setZoneType(String zoneType) {
        this.zoneType = zoneType;
    }

    @Override
    public double getMonthlyPayment() {
        double monthlyInterestRate = this.getInterestRate() / 100 / 12;
        double baseMonthlyPayment = (super.getPropertyValue() / (super.getLoanTerm() * 12)) * (1 + monthlyInterestRate);
        return baseMonthlyPayment * 1.02;
    }
}