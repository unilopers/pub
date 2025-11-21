package com.grupo_5.pub.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReceitaBebidaId implements Serializable {

    @Column(name = "id_bebida")
    private Integer idBebida;

    @Column(name = "id_ingrediente")
    private Integer idIngrediente;

    public ReceitaBebidaId() {
    }

    public ReceitaBebidaId(Integer idBebida, Integer idIngrediente) {
        this.idBebida = idBebida;
        this.idIngrediente = idIngrediente;
    }

    public Integer getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(Integer idBebida) {
        this.idBebida = idBebida;
    }

    public Integer getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReceitaBebidaId)) return false;
        ReceitaBebidaId that = (ReceitaBebidaId) o;
        return Objects.equals(idBebida, that.idBebida) &&
                Objects.equals(idIngrediente, that.idIngrediente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBebida, idIngrediente);
    }
}
