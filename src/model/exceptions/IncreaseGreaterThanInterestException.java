package model.exceptions;

/**
 * Exceção lançada quando o valor do acréscimo é maior do que o valor dos juros da mensalidade.
 */
public class IncreaseGreaterThanInterestException extends Exception {
    /**
     * Construtor que aceita uma mensagem de erro.
     *
     * @param message A mensagem de erro.
     */
    public IncreaseGreaterThanInterestException(String message) {
        super(message);
    }
}