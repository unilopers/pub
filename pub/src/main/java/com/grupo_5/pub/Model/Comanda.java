package com.grupo_5.pub.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comanda")
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_mesa")
    private Mesa mesa;

    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;

    private String status;

    private BigDecimal valorSubtotal;
    private BigDecimal valorDesconto;
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL)
    private List<ItemComanda> itens;

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL)
    private List<PromocaoAplicada> promocoes;

    // GETTERS E SETTERS
}
