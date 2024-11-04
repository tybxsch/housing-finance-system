package util;

/**
 * Classe utilitária para exibição de mensagens ao usuário.
 */
public class UserInterface {

    public double getPropertyValue() {
        return InputValidator.getPositiveDouble("Digite o valor do imóvel: ");
    }

    public int getLoanTerm() {
        return InputValidator.getPositiveInt("Digite o prazo do financiamento em anos: ");
    }

    public double getInterestRate() {
        return InputValidator.getReasonableInterestRate("Digite a taxa de juros anual: ");
    }
}