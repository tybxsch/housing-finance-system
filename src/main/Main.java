package main;

import app.Application;

/**
 * Classe principal que executa o programa de financiamento.
 */
public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        System.out.println("Bem-vindo ao programa de financiamento! - running from Main.java");
        app.run();
    }
}
