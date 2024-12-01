package model;
import constants.FormattingConstants;
import util.CurrencyFormatter;

import java.io.Serializable;


/**
 * Classe que representa um financiamento de terreno, incluindo um acréscimo de 2% sobre o valor da parcela.
 */
public class Land extends Financing implements Serializable {
    private static final long serialVersionUID = 1L;
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
     * Getters e setters.
     */
    public String getZoneType() {
        return zoneType;
    }

    public void setZoneType(String zoneType) {
        this.zoneType = zoneType;
    }

    /**
     * Calcula o pagamento mensal do financiamento.
     *
     * @return O valor do pagamento mensal.
     */
    @Override
    public double getMonthlyPayment() {
        double monthlyInterestRate = this.getInterestRate() / 100 / 12;
        double baseMonthlyPayment = (super.getPropertyValue() / (super.getLoanTerm() * 12)) * (1 + monthlyInterestRate);
        return baseMonthlyPayment * 1.02;
    }

    /**
     * Transforma em string com formatação.
     *
     * @return String formatada.
     */
    @Override
    public String toString() {
        return String.format(
                FormattingConstants.SEPARATOR_LINE + "\n" +
                        "         Detalhes do Financiamento - Terreno         \n" +
                        "Tipo de imóvel: Terreno\n" +
                        "Valor do imóvel: %s\n" +
                        "Prazo: %d anos\n" +
                        "Taxa de juros anual: %.2f%%\n" +
                        "Tipo de zona: %s\n" +
                        "Pagamento mensal: %s\n" +
                        "Pagamento total: %s\n" +
                        FormattingConstants.SEPARATOR_LINE + "\n",
                CurrencyFormatter.formatToBRL(super.getPropertyValue()),
                super.getLoanTerm(),
                super.getInterestRate(),
                this.zoneType,
                CurrencyFormatter.formatToBRL(this.getMonthlyPayment()),
                CurrencyFormatter.formatToBRL(super.getTotalPayment())
        );
    }
}