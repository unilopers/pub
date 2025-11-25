package com.grupo_5.pub.Controller;

import com.grupo_5.pub.Model.*;
import com.grupo_5.pub.Repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/comandas")
public class ComandaController {

    private final ComandaRepository comandaRepo;
    private final ItemComandaRepository itemRepo;
    private final BebidaRepository bebidaRepo;
    private final PromocaoRepository promocaoRepo;
    private final PromocaoAplicadaRepository promoAplicadaRepo;

    public ComandaController(
            ComandaRepository comandaRepo,
            ItemComandaRepository itemRepo,
            BebidaRepository bebidaRepo,
            PromocaoRepository promocaoRepo,
            PromocaoAplicadaRepository promoAplicadaRepo) {

        this.comandaRepo = comandaRepo;
        this.itemRepo = itemRepo;
        this.bebidaRepo = bebidaRepo;
        this.promocaoRepo = promocaoRepo;
        this.promoAplicadaRepo = promoAplicadaRepo;
    }

    // ---------------------------------------------------------
    // 1) ABRIR COMANDA
    // ---------------------------------------------------------
    @PostMapping("/abrir")
    public ResponseEntity<?> abrir(@RequestParam Long idCliente,
                                   @RequestParam Long idMesa) {

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
    public ResponseEntity<?> addItem(@PathVariable Long id,
                                     @RequestParam Long idBebida,
                                     @RequestParam Integer qtd) {

        Optional<Comanda> comandaOpt = comandaRepo.findById(id);
        Optional<Bebida> bebidaOpt = bebidaRepo.findById(idBebida);

        if (comandaOpt.isEmpty() || bebidaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Comanda ou bebida não encontrada");
        }

        Comanda c = comandaOpt.get();
        Bebida b = bebidaOpt.get();

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
    public ResponseEntity<?> deleteItem(@PathVariable Long id,
                                        @PathVariable Long itemId) {

        Optional<ItemComanda> itemOpt = itemRepo.findById(itemId);
        Optional<Comanda> comandaOpt = comandaRepo.findById(id);

        if (itemOpt.isEmpty() || comandaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Item ou comanda não encontrados");
        }

        ItemComanda item = itemOpt.get();
        Comanda c = comandaOpt.get();

        c.setValorSubtotal(c.getValorSubtotal().subtract(item.getValorItem()));
        c.setValorTotal(c.getValorSubtotal());

        itemRepo.delete(item);
        comandaRepo.save(c);

        return ResponseEntity.ok("Item removido");
    }

    // ---------------------------------------------------------
    // 4) FECHAR COMANDA (com ou sem promoção)
    // ---------------------------------------------------------
    @PostMapping("/{id}/fechar")
    public ResponseEntity<?> fechar(@PathVariable Long id,
                                    @RequestParam(required = false) Long idPromocao) {

        Optional<Comanda> opt = comandaRepo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.badRequest().body("Comanda não encontrada");

        Comanda c = opt.get();

        // subtotal já está calculado ao adicionar/remover itens

        BigDecimal desconto = BigDecimal.ZERO;

        if (idPromocao != null) {
            Optional<Promocao> promoOpt = promocaoRepo.findById(idPromocao);
            if (promoOpt.isPresent()) {

                Promocao p = promoOpt.get();

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

        comandaRepo.save(c);

        return ResponseEntity.ok(c);
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
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return ResponseEntity.of(comandaRepo.findById(id));
    }
}
