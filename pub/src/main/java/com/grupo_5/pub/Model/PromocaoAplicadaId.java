package com.grupo_5.pub.Model;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class PromocaoAplicadaId implements Serializable {

    private Long idComanda;
    private Long idPromocao;

    // GETTERS, SETTERS, equals() E hashCode()
}

