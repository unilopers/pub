package com.grupo_5.pub.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "BebidasDoEvento")
public class BebidasDoEvento {

    @EmbeddedId
    private BebidasDoEventoId id = new BebidasDoEventoId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEvento")
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idBebida")
    @JoinColumn(name = "id_bebida", nullable = false)
    private Bebida bebida;

    @Column(name = "preco_promocional", precision = 10, scale = 2)
    private BigDecimal precoPromocional;

    @Column(name = "is_destaque")
    private Boolean destaque;

    public BebidasDoEvento() {
    }

    public BebidasDoEvento(Evento evento, Bebida bebida, BigDecimal precoPromocional, Boolean destaque) {
        this.evento = evento;
        this.bebida = bebida;
        this.precoPromocional = precoPromocional;
        this.destaque = destaque;

        this.id = new BebidasDoEventoId(
                evento != null ? evento.getId() : null,
                bebida != null ? bebida.getId() : null
        );
    }

    public BebidasDoEventoId getId() {
        return id;
    }

    public void setId(BebidasDoEventoId id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
        if (evento != null) {
            this.id.setIdEvento(evento.getId());
        }
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

    public BigDecimal getPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(BigDecimal precoPromocional) {
        this.precoPromocional = precoPromocional;
    }

    public Boolean getDestaque() {
        return destaque;
    }

    public void setDestaque(Boolean destaque) {
        this.destaque = destaque;
    }
}
