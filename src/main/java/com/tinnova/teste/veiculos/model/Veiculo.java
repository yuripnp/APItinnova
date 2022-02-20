package com.tinnova.teste.veiculos.model;

import com.tinnova.teste.veiculos.enums.CorType;
import com.tinnova.teste.veiculos.enums.MarcaType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "veiculo")
    private String veiculo;

    @Column(name = "marca")
    @Enumerated(EnumType.STRING)
    private MarcaType marca;

    @Column(name = "ano")
    private int ano;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "cor")
    @Enumerated(EnumType.STRING)
    private CorType cor;

    @Column(name = "vendido")
    private boolean vendido;

    @Column(name = "created")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated")
    @UpdateTimestamp
    private LocalDateTime updated;

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", veiculo='" + veiculo + '\'' +
                ", marca=" + marca +
                ", ano=" + ano +
                ", descricao='" + descricao + '\'' +
                ", cor=" + cor +
                ", vendido=" + vendido +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    public Veiculo() {
    }

    public Veiculo(String veiculo, MarcaType marca, int ano, String descricao, CorType cor, boolean vendido) {
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.descricao = descricao;
        this.cor = cor;
        this.vendido = vendido;
    }

    public Veiculo(Long id, String veiculo, MarcaType marca, int ano, String descricao, CorType cor, boolean vendido, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.descricao = descricao;
        this.cor = cor;
        this.vendido = vendido;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public CorType getCor() {
        return cor;
    }

    public void setCor(CorType cor) {
        this.cor = cor;
    }
}
