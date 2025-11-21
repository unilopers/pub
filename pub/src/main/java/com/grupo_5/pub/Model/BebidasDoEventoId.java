package com.grupo_5.pub.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BebidasDoEventoId implements Serializable {

    @Column(name = "id_evento")
    private Integer idEvento;

    @Column(name = "id_bebida")
    private Integer idBebida;

    public BebidasDoEventoId() {
    }

    public BebidasDoEventoId(Integer idEvento, Integer idBebida) {
        this.idEvento = idEvento;
        this.idBebida = idBebida;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(Integer idBebida) {
        this.idBebida = idBebida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BebidasDoEventoId)) return false;
        BebidasDoEventoId that = (BebidasDoEventoId) o;
        return Objects.equals(idEvento, that.idEvento) &&
                Objects.equals(idBebida, that.idBebida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, idBebida);
    }
}
