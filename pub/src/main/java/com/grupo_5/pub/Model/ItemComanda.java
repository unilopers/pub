package com.grupo_5.pub.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_comanda")
public class ItemComanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_comanda")
    private Comanda comanda;

    @ManyToOne
    @JoinColumn(name = "id_bebida")
    private Bebida bebida;

    private Integer quantidade;

    private BigDecimal precoUnitarioRegistro;

    private BigDecimal valorItem;

    // GETTERS E SETTERS
}
