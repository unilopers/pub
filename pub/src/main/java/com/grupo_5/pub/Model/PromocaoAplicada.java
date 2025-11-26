package com.grupo_5.pub.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "promocao_aplicada")
public class PromocaoAplicada {

    @EmbeddedId
    private PromocaoAplicadaId id;

    @ManyToOne
    @MapsId("idComanda")
    @JoinColumn(name = "id_comanda")
    private Comanda comanda;

    @ManyToOne
    @MapsId("idPromocao")
    @JoinColumn(name = "id_promocao")
    private Promocao promocao;

    private LocalDateTime dataAplicacao;

    private BigDecimal valorDescontoAplicado;

    // GETTERS E SETTERS

    public PromocaoAplicadaId getId() {
        return id;
    }

    public void setId(PromocaoAplicadaId id) {
        this.id = id;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public LocalDateTime getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDateTime dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public BigDecimal getValorDescontoAplicado() {
        return valorDescontoAplicado;
    }

    public void setValorDescontoAplicado(BigDecimal valorDescontoAplicado) {
        this.valorDescontoAplicado = valorDescontoAplicado;
    }
}
package com.grupo_5.pub.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "promocao_aplicada")
public class PromocaoAplicada {

    @EmbeddedId
    private PromocaoAplicadaId id;

    @ManyToOne
    @MapsId("idComanda")
    @JoinColumn(name = "id_comanda")
    private Comanda comanda;

    @ManyToOne
    @MapsId("idPromocao")
    @JoinColumn(name = "id_promocao")
    private Promocao promocao;

    private LocalDateTime dataAplicacao;

    private BigDecimal valorDescontoAplicado;

    // GETTERS E SETTERS

    public PromocaoAplicadaId getId() {
        return id;
    }

    public void setId(PromocaoAplicadaId id) {
        this.id = id;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public LocalDateTime getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDateTime dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public BigDecimal getValorDescontoAplicado() {
        return valorDescontoAplicado;
    }

    public void setValorDescontoAplicado(BigDecimal valorDescontoAplicado) {
        this.valorDescontoAplicado = valorDescontoAplicado;
    }
}
