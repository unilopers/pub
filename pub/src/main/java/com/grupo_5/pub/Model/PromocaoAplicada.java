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
}
