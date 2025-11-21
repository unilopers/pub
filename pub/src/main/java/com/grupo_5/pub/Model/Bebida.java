package com.grupo_5.pub.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Bebida")
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @OneToMany(mappedBy = "bebida", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceitaBebida> receita = new ArrayList<>();

    @OneToMany(mappedBy = "bebida", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BebidasDoEvento> bebidasEmEventos = new ArrayList<>();

    public Bebida() {
    }

    public Bebida(Integer id, String nome, BigDecimal preco, String tipo, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
        this.descricao = descricao;
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<ReceitaBebida> getReceita() {
        return receita;
    }

    public void setReceita(List<ReceitaBebida> receita) {
        this.receita = receita;
    }

    public List<BebidasDoEvento> getBebidasEmEventos() {
        return bebidasEmEventos;
    }

    public void setBebidasEmEventos(List<BebidasDoEvento> bebidasEmEventos) {
        this.bebidasEmEventos = bebidasEmEventos;
    }

    public void adicionarIngredienteNaReceita(ReceitaBebida receitaBebida) {
        this.receita.add(receitaBebida);
        receitaBebida.setBebida(this);
    }

    public void removerIngredienteDaReceita(ReceitaBebida receitaBebida) {
        this.receita.remove(receitaBebida);
        receitaBebida.setBebida(null);
    }

    public void adicionarEmEvento(BebidasDoEvento bebidasDoEvento) {
        this.bebidasEmEventos.add(bebidasDoEvento);
        bebidasDoEvento.setBebida(this);
    }

    public void removerDeEvento(BebidasDoEvento bebidasDoEvento) {
        this.bebidasEmEventos.remove(bebidasDoEvento);
        bebidasDoEvento.setBebida(null);
    }
}
