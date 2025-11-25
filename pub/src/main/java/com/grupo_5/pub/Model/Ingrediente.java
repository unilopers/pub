package com.grupo_5.pub.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Ingrediente")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String unidadeMedida;

    private double estoqueAtual;

    private double estoqueMinimo;

    public Ingrediente() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }

    public Double getEstoqueAtual() { return estoqueAtual; }
    public void setEstoqueAtual(Double estoqueAtual) { this.estoqueAtual = estoqueAtual; }

    public Double getEstoqueMinimo() { return estoqueMinimo; }
    public void setEstoqueMinimo(Double estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }
}
