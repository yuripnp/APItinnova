package com.tinnova.teste.veiculos.enums;

public enum CorType {
    BRANCO("BRANCO"),
    PRETO("PRETO"),
    VERMELHO("VERMELHO"),
    AZUL("AZUL"),
    VERDE("VERDE"),
    AMARELO("AMARELO");

    public final String label;

    CorType(String label) {
        this.label = label;
    }
}
