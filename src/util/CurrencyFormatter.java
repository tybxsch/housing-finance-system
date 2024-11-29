// src/util/CurrencyFormatter.java
package util;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {

    /**
     * Formata um valor double para o formato de moeda brasileira (Real - BRL).
     *
     * @param value O valor a ser formatado.
     * @return O valor formatado como moeda brasileira.
     */
    public static String formatToBRL(double value) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return currencyFormatter.format(value);
    }
}