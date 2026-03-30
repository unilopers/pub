package com.grupo_5.pub.Workers;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public class WebhookPayload {

    private String evento;           
    private Integer idComanda;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataFechamento;

    private BigDecimal valorSubtotal;
    private BigDecimal valorDesconto;
    private BigDecimal valorTotal;

    private String origem;           
    private String versao;           

    public WebhookPayload() {}

    public WebhookPayload(Integer idComanda, String status,
                          LocalDateTime dataFechamento,
                          BigDecimal valorSubtotal,
                          BigDecimal valorDesconto,
                          BigDecimal valorTotal) {
        this.evento        = "COMANDA_FECHADA";
        this.idComanda     = idComanda;
        this.status        = status;
        this.dataFechamento = dataFechamento;
        this.valorSubtotal = valorSubtotal;
        this.valorDesconto = valorDesconto;
        this.valorTotal    = valorTotal;
        this.origem        = "pub-api";
        this.versao        = "1.0";
    }


    public String getEvento() { return evento; }
    public void setEvento(String evento) { this.evento = evento; }

    public Integer getIdComanda() { return idComanda; }
    public void setIdComanda(Integer idComanda) { this.idComanda = idComanda; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(LocalDateTime dataFechamento) { this.dataFechamento = dataFechamento; }

    public BigDecimal getValorSubtotal() { return valorSubtotal; }
    public void setValorSubtotal(BigDecimal valorSubtotal) { this.valorSubtotal = valorSubtotal; }

    public BigDecimal getValorDesconto() { return valorDesconto; }
    public void setValorDesconto(BigDecimal valorDesconto) { this.valorDesconto = valorDesconto; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public String getVersao() { return versao; }
    public void setVersao(String versao) { this.versao = versao; }
}