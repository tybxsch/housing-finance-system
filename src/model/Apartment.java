package model;

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
     * Getters e setters para os novos atributos.
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
        double monthlyInterestRate = super.getInterestRate() / 100 / 2;
        int months = this.getLoanTerm() * 12;
        double numerator = super.getPropertyValue() * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, months);
        double denominator = Math.pow(1 + monthlyInterestRate, months) - 1;
        return numerator / denominator;
    }
}