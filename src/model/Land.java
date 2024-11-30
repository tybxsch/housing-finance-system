package model;
import constants.FormattingConstants;
import util.CurrencyFormatter;


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
     * Exibe os detalhes do financiamento do terreno.
     *
     * @param financingNumber Número do financiamento.
     */
    @Override
    public void getFinancingDetails(int financingNumber) {
        System.out.println(FormattingConstants.SEPARATOR_LINE);
        System.out.printf("         Detalhes do Financiamento %d - %s         \n", financingNumber, "Terreno");
        System.out.println("Tipo de imóvel: Terreno");
        System.out.println("Valor do imóvel: " + CurrencyFormatter.formatToBRL(super.getPropertyValue()));
        System.out.println("Prazo: " + super.getLoanTerm() + " anos");
        System.out.println("Taxa de juros anual: " + super.getInterestRate() + "%");
        System.out.println("Tipo de zona: " + this.zoneType);
        System.out.println("Pagamento mensal: " + CurrencyFormatter.formatToBRL(this.getMonthlyPayment()));
        System.out.println("Pagamento total: " + CurrencyFormatter.formatToBRL(super.getTotalPayment()));
        System.out.printf(FormattingConstants.SEPARATOR_LINE);
        System.out.println();
    }
}