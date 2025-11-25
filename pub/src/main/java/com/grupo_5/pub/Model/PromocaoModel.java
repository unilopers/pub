package com.grupo_5.pub.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="Promocao")
public class PromocaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Lob
    private String descricao;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(name = "tipo_desconto",nullable = false, length = 20)
    private String tipoDesconto;

    @Column(name = "valor_desconto", precision = 5, scale = 2)
    private BigDecimal valorDesconto;


    public PromocaoModel() {}

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public LocalDate getDataInicio() {return dataInicio;}
    public void setDataInicio(LocalDate dataInicio) {this.dataInicio = dataInicio;}

    public LocalDate getDataFim() {return dataFim;}
    public void setDataFim(LocalDate dataFim) {this.dataFim = dataFim;}

    public String getTipoDesconto() {return tipoDesconto;}
    public void setTipoDesconto(String tipoDesconto) {this.tipoDesconto = tipoDesconto;}

    public BigDecimal getValorDesconto() {return valorDesconto;}
    public void setValorDesconto(BigDecimal valorDesconto) {this.valorDesconto = valorDesconto;}
}