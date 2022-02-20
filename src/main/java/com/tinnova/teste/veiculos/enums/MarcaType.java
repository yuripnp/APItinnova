package com.tinnova.teste.veiculos.enums;

public enum MarcaType {
    HONDA("HONDA"),
    FIAT("FIAT"),
    FORD("FORD"),
    CHEVROLET("CHEVROLET"),
    VOLKSWAGEN("VOLKSWAGEN");

    public final String label;

    MarcaType(String label) {
        this.label = label;
    }
}
