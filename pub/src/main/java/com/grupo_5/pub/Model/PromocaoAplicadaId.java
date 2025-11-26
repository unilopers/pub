package com.grupo_5.pub.Model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PromocaoAplicadaId implements Serializable {

    private Integer idComanda;  // Mudado para Integer
    private Integer idPromocao; // Mudado para Integer

    // Construtor Vazio
    public PromocaoAplicadaId() {
    }

    public PromocaoAplicadaId(Integer idComanda, Integer idPromocao) {
        this.idComanda = idComanda;
        this.idPromocao = idPromocao;
    }

    // --- GETTERS E SETTERS (O que faltava) ---

    public Integer getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(Integer idComanda) {
        this.idComanda = idComanda;
    }

    public Integer getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(Integer idPromocao) {
        this.idPromocao = idPromocao;
    }

    // --- EQUALS e HASHCODE (Obrigatórios para @EmbeddedId) ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromocaoAplicadaId that = (PromocaoAplicadaId) o;
        return Objects.equals(idComanda, that.idComanda) &&
                Objects.equals(idPromocao, that.idPromocao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComanda, idPromocao);
    }
}
