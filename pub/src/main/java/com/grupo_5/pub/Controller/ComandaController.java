package com.grupo_5.pub.Controller;
<<<<<<< HEAD

import com.grupo_5.pub.Model.*;
import com.grupo_5.pub.Repository.*;
=======
import com.grupo_5.pub.Model.*;
import com.grupo_5.pub.Repository.*;

import com.grupo_5.pub.Workers.WebhookPayload;
import com.grupo_5.pub.Workers.WebhookWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> 9f6a77b (subindo projeto para fork)
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/comandas")
public class ComandaController {

<<<<<<< HEAD
=======
    private static final Logger log = LoggerFactory.getLogger(ComandaController.class);

>>>>>>> 9f6a77b (subindo projeto para fork)
    private final ComandaRepository comandaRepo;
    private final ItemComandaRepository itemRepo;
    private final BebidaRepository bebidaRepo;
    private final PromocaoRepository promocaoRepo;
    private final PromocaoAplicadaRepository promoAplicadaRepo;

<<<<<<< HEAD
=======
    private final WebhookWorker webhookWorker;

>>>>>>> 9f6a77b (subindo projeto para fork)
    public ComandaController(
            ComandaRepository comandaRepo,
            ItemComandaRepository itemRepo,
            BebidaRepository bebidaRepo,
            PromocaoRepository promocaoRepo,
<<<<<<< HEAD
            PromocaoAplicadaRepository promoAplicadaRepo) {

        this.comandaRepo = comandaRepo;
        this.itemRepo = itemRepo;
        this.bebidaRepo = bebidaRepo;
        this.promocaoRepo = promocaoRepo;
        this.promoAplicadaRepo = promoAplicadaRepo;
=======
            PromocaoAplicadaRepository promoAplicadaRepo,
            WebhookWorker webhookWorker) {

        this.comandaRepo      = comandaRepo;
        this.itemRepo         = itemRepo;
        this.bebidaRepo       = bebidaRepo;
        this.promocaoRepo     = promocaoRepo;
        this.promoAplicadaRepo = promoAplicadaRepo;
        this.webhookWorker    = webhookWorker;
>>>>>>> 9f6a77b (subindo projeto para fork)
    }

    // ---------------------------------------------------------
    // 1) ABRIR COMANDA
    // ---------------------------------------------------------
    @PostMapping("/abrir")
    public ResponseEntity<?> abrir(@RequestParam Integer idCliente,
                                   @RequestParam Integer idMesa) {

        Comanda c = new Comanda();
        c.setStatus("ABERTA");
        c.setDataAbertura(LocalDateTime.now());
        c.setValorSubtotal(BigDecimal.ZERO);
        c.setValorDesconto(BigDecimal.ZERO);
        c.setValorTotal(BigDecimal.ZERO);

        return ResponseEntity.ok(comandaRepo.save(c));
    }

    // ---------------------------------------------------------
    // 2) ADICIONAR ITEM
    // ---------------------------------------------------------
    @PostMapping("/{id}/itens")
    public ResponseEntity<?> addItem(@PathVariable Integer id,
                                     @RequestParam Integer idBebida,
                                     @RequestParam Integer qtd) {

        Optional<Comanda> comandaOpt = comandaRepo.findById(id);
<<<<<<< HEAD
        Optional<Bebida> bebidaOpt = bebidaRepo.findById(idBebida);
=======
        Optional<Bebida> bebidaOpt   = bebidaRepo.findById(idBebida);
>>>>>>> 9f6a77b (subindo projeto para fork)

        if (comandaOpt.isEmpty() || bebidaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Comanda ou bebida não encontrada");
        }

        Comanda c = comandaOpt.get();
<<<<<<< HEAD
        Bebida b = bebidaOpt.get();
=======
        Bebida  b = bebidaOpt.get();
>>>>>>> 9f6a77b (subindo projeto para fork)

        ItemComanda item = new ItemComanda();
        item.setComanda(c);
        item.setBebida(b);
        item.setQuantidade(qtd);
        item.setPrecoUnitarioRegistro(b.getPreco());
        item.setValorItem(b.getPreco().multiply(BigDecimal.valueOf(qtd)));

        itemRepo.save(item);

        c.setValorSubtotal(c.getValorSubtotal().add(item.getValorItem()));
        c.setValorTotal(c.getValorSubtotal());

        comandaRepo.save(c);

        return ResponseEntity.ok(item);
    }

    // ---------------------------------------------------------
    // 3) REMOVER ITEM
    // ---------------------------------------------------------
    @DeleteMapping("/{id}/itens/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id,
                                        @PathVariable Integer itemId) {

<<<<<<< HEAD
        Optional<ItemComanda> itemOpt = itemRepo.findById(itemId);
        Optional<Comanda> comandaOpt = comandaRepo.findById(id);
=======
        Optional<ItemComanda> itemOpt    = itemRepo.findById(itemId);
        Optional<Comanda>     comandaOpt = comandaRepo.findById(id);
>>>>>>> 9f6a77b (subindo projeto para fork)

        if (itemOpt.isEmpty() || comandaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Item ou comanda não encontrados");
        }

        ItemComanda item = itemOpt.get();
<<<<<<< HEAD
        Comanda c = comandaOpt.get();
=======
        Comanda     c    = comandaOpt.get();
>>>>>>> 9f6a77b (subindo projeto para fork)

        c.setValorSubtotal(c.getValorSubtotal().subtract(item.getValorItem()));
        c.setValorTotal(c.getValorSubtotal());

        itemRepo.delete(item);
        comandaRepo.save(c);

        return ResponseEntity.ok("Item removido");
    }

    // ---------------------------------------------------------
<<<<<<< HEAD
    // 4) FECHAR COMANDA (com ou sem promoção)
=======
    // 4) FECHAR COMANDA — INTEGRAÇÃO ASSÍNCRONA ADICIONADA AQUI
>>>>>>> 9f6a77b (subindo projeto para fork)
    // ---------------------------------------------------------
    @PostMapping("/{id}/fechar")
    public ResponseEntity<?> fechar(@PathVariable Integer id,
                                    @RequestParam(required = false) Integer idPromocao) {

        Optional<Comanda> opt = comandaRepo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.badRequest().body("Comanda não encontrada");

        Comanda c = opt.get();
<<<<<<< HEAD

        // subtotal já está calculado ao adicionar/remover itens

=======
>>>>>>> 9f6a77b (subindo projeto para fork)
        BigDecimal desconto = BigDecimal.ZERO;

        if (idPromocao != null) {
            Optional<Promocao> promoOpt = promocaoRepo.findById(idPromocao);
            if (promoOpt.isPresent()) {

                Promocao p = promoOpt.get();
<<<<<<< HEAD

=======
>>>>>>> 9f6a77b (subindo projeto para fork)
                desconto = c.getValorSubtotal()
                        .multiply(p.getValorDesconto().divide(BigDecimal.valueOf(100)));

                PromocaoAplicadaId pk = new PromocaoAplicadaId();
                pk.setIdComanda(id);
                pk.setIdPromocao(idPromocao);

                PromocaoAplicada pa = new PromocaoAplicada();
                pa.setId(pk);
                pa.setComanda(c);
                pa.setPromocao(p);
                pa.setDataAplicacao(LocalDateTime.now());
                pa.setValorDescontoAplicado(desconto);

                promoAplicadaRepo.save(pa);
            }
        }

        c.setValorDesconto(desconto);
        c.setValorTotal(c.getValorSubtotal().subtract(desconto));
        c.setStatus("FECHADA");
        c.setDataFechamento(LocalDateTime.now());

<<<<<<< HEAD
        comandaRepo.save(c);

        return ResponseEntity.ok(c);
=======
        Comanda comandaSalva = comandaRepo.save(c);

        WebhookPayload payload = new WebhookPayload(
                comandaSalva.getId(),
                comandaSalva.getStatus(),
                comandaSalva.getDataFechamento(),
                comandaSalva.getValorSubtotal(),
                comandaSalva.getValorDesconto(),
                comandaSalva.getValorTotal()
        );


        log.info("[ComandaController] Comanda {} fechada. Disparando webhook assíncrono...",
                 comandaSalva.getId());
        webhookWorker.notificarFechamentoComanda(payload);

        return ResponseEntity.ok(comandaSalva);
>>>>>>> 9f6a77b (subindo projeto para fork)
    }

    // ---------------------------------------------------------
    // 5) LISTAR TODAS
    // ---------------------------------------------------------
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(comandaRepo.findAll());
    }

    // ---------------------------------------------------------
    // 6) BUSCAR POR ID
    // ---------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        return ResponseEntity.of(comandaRepo.findById(id));
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 9f6a77b (subindo projeto para fork)
