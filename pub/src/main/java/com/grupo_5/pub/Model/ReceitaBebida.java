package com.grupo_5.pub.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ReceitaBebida")
public class ReceitaBebida {

    @EmbeddedId
    private ReceitaBebidaId id = new ReceitaBebidaId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idBebida")
    @JoinColumn(name = "id_bebida", nullable = false)
    private Bebida bebida;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idIngrediente")
    @JoinColumn(name = "id_ingrediente", nullable = false)
    private Ingrediente ingrediente;

    @Column(name = "quantidade_necessaria", precision = 10, scale = 2)
    private BigDecimal quantidadeNecessaria;

    public ReceitaBebida() {
    }

    public ReceitaBebida(Bebida bebida, Ingrediente ingrediente, BigDecimal quantidadeNecessaria) {
        this.bebida = bebida;
        this.ingrediente = ingrediente;
        this.quantidadeNecessaria = quantidadeNecessaria;

        this.id = new ReceitaBebidaId(
                bebida != null ? bebida.getId() : null,
                ingrediente != null ? ingrediente.getId() : null
        );
    }

    public ReceitaBebidaId getId() {
        return id;
    }

    public void setId(ReceitaBebidaId id) {
        this.id = id;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
        if (bebida != null) {
            this.id.setIdBebida(bebida.getId());
        }
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
        if (ingrediente != null) {
            this.id.setIdIngrediente(ingrediente.getId());
        }
    }

    public BigDecimal getQuantidadeNecessaria() {
        return quantidadeNecessaria;
    }

    public void setQuantidadeNecessaria(BigDecimal quantidadeNecessaria) {
        this.quantidadeNecessaria = quantidadeNecessaria;
    }
}
