package com.grupo_5.pub.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Ingrediente")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @Column(name = "unidade_medida", nullable = false, length = 20)
    private String unidadeMedida;

    @Column(name = "estoque_atual", precision = 10, scale = 2)
    private BigDecimal estoqueAtual;

    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceitaBebida> receitas = new ArrayList<>();

    public Ingrediente() {
    }

    public Ingrediente(Integer id, String nome, String unidadeMedida, BigDecimal estoqueAtual) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.estoqueAtual = estoqueAtual;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public BigDecimal getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(BigDecimal estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public List<ReceitaBebida> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<ReceitaBebida> receitas) {
        this.receitas = receitas;
    }
}
