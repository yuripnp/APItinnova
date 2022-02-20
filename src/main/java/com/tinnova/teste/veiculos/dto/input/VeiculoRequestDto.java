package com.tinnova.teste.veiculos.dto.input;

import com.tinnova.teste.veiculos.enums.CorType;
import com.tinnova.teste.veiculos.enums.MarcaType;

public class VeiculoRequestDto {
    private String veiculo;

    private MarcaType marca;

    private int ano;

    private String descricao;

    private CorType cor;

    private boolean vendido;

    public VeiculoRequestDto(String veiculo, MarcaType marca, int ano, String descricao, CorType cor, boolean vendido) {
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.descricao = descricao;
        this.cor = cor;
        this.vendido = vendido;
    }

    public VeiculoRequestDto() {
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public MarcaType getMarca() {
        return marca;
    }

    public void setMarca(MarcaType marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public CorType getCor() {
        return cor;
    }

    public void setCor(CorType cor) {
        this.cor = cor;
    }
}
