package com.grupo_5.pub.Controller;

import com.grupo_5.pub.Model.*;
import com.grupo_5.pub.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/bebidas", produces = MediaType.APPLICATION_XML_VALUE)
public class BebidaController {

    private final BebidaRepository bebidaRepository;
    private final IngredienteRepository ingredienteRepository;
    private final ReceitaBebidaRepository receitaBebidaRepository;
    private final EventoRepository eventoRepository;
    private final BebidasDoEventoRepository bebidasDoEventoRepository;

    public BebidaController(BebidaRepository bebidaRepository,
                            IngredienteRepository ingredienteRepository,
                            ReceitaBebidaRepository receitaBebidaRepository,
                            EventoRepository eventoRepository,
                            BebidasDoEventoRepository bebidasDoEventoRepository) {
        this.bebidaRepository = bebidaRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.receitaBebidaRepository = receitaBebidaRepository;
        this.eventoRepository = eventoRepository;
        this.bebidasDoEventoRepository = bebidasDoEventoRepository;
    }

    // CREATE
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Bebida> criarBebida(@RequestBody Bebida bebida) {
        Bebida salva = bebidaRepository.save(bebida);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    // listar todas
    @GetMapping
    public ResponseEntity<List<Bebida>> listarTodas() {
        List<Bebida> bebidas = bebidaRepository.findAll();
        return ResponseEntity.ok(bebidas);
    }

    // buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Bebida> buscarPorId(@PathVariable Integer id) {
        Optional<Bebida> bebidaOpt = bebidaRepository.findById(id);
        return bebidaOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Bebida> atualizarBebida(@PathVariable Integer id,
                                                  @RequestBody Bebida atualizada) {
        Optional<Bebida> bebidaOpt = bebidaRepository.findById(id);
        if (bebidaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Bebida existente = bebidaOpt.get();
        existente.setNome(atualizada.getNome());
        existente.setPreco(atualizada.getPreco());
        existente.setTipo(atualizada.getTipo());
        existente.setDescricao(atualizada.getDescricao());

        Bebida salva = bebidaRepository.save(existente);
        return ResponseEntity.ok(salva);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBebida(@PathVariable Integer id) {
        if (!bebidaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bebidaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idBebida}/ingredientes")
    @Transactional
    public ResponseEntity<Bebida> adicionarIngredienteNaReceita(
            @PathVariable Integer idBebida,
            @RequestParam("idIngrediente") Integer idIngrediente,
            @RequestParam("quantidade") BigDecimal quantidade) {

        Optional<Bebida> bebidaOpt = bebidaRepository.findById(idBebida);
        if (bebidaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Ingrediente> ingredienteOpt = ingredienteRepository.findById(idIngrediente);
        if (ingredienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Bebida bebida = bebidaOpt.get();
        Ingrediente ingrediente = ingredienteOpt.get();

        ReceitaBebidaId id = new ReceitaBebidaId(bebida.getId(), ingrediente.getId());
        Optional<ReceitaBebida> receitaExistente = receitaBebidaRepository.findById(id);

        if (receitaExistente.isPresent()) {

            ReceitaBebida receita = receitaExistente.get();
            receita.setQuantidadeNecessaria(quantidade);
            receitaBebidaRepository.save(receita);
        } else {

            ReceitaBebida receita = new ReceitaBebida();
            receita.setBebida(bebida);
            receita.setIngrediente(ingrediente);
            receita.setQuantidadeNecessaria(quantidade);

            receitaBebidaRepository.save(receita);
        }

        Bebida bebidaAtualizada = bebidaRepository.findById(idBebida).orElse(bebida);
        return ResponseEntity.ok(bebidaAtualizada);
    }

    @DeleteMapping("/{idBebida}/ingredientes/{idIngrediente}")
    @Transactional
    public ResponseEntity<Void> removerIngredienteDaReceita(
            @PathVariable Integer idBebida,
            @PathVariable Integer idIngrediente) {

        ReceitaBebidaId id = new ReceitaBebidaId(idBebida, idIngrediente);
        if (!receitaBebidaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        receitaBebidaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idBebida}/eventos/{idEvento}/destaque")
    @Transactional
    public ResponseEntity<Bebida> marcarComoDestaqueEmEvento(
            @PathVariable Integer idBebida,
            @PathVariable Integer idEvento,
            @RequestParam(value = "precoPromocional", required = false) BigDecimal precoPromocional) {

        Optional<Bebida> bebidaOpt = bebidaRepository.findById(idBebida);
        if (bebidaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Evento> eventoOpt = eventoRepository.findById(idEvento);
        if (eventoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Bebida bebida = bebidaOpt.get();
        Evento evento = eventoOpt.get();

        BebidasDoEventoId id = new BebidasDoEventoId(evento.getId(), bebida.getId());
        Optional<BebidasDoEvento> existenteOpt = bebidasDoEventoRepository.findById(id);

        BebidasDoEvento relacao;
        if (existenteOpt.isPresent()) {
            relacao = existenteOpt.get();
        } else {
            relacao = new BebidasDoEvento();
            relacao.setEvento(evento);
            relacao.setBebida(bebida);
        }

        relacao.setDestaque(true);
        relacao.setPrecoPromocional(precoPromocional);

        bebidasDoEventoRepository.save(relacao);

        Bebida bebidaAtualizada = bebidaRepository.findById(idBebida).orElse(bebida);
        return ResponseEntity.ok(bebidaAtualizada);
    }

    @DeleteMapping("/{idBebida}/eventos/{idEvento}/destaque")
    @Transactional
    public ResponseEntity<Void> removerDestaqueDeEvento(
            @PathVariable Integer idBebida,
            @PathVariable Integer idEvento) {

        BebidasDoEventoId id = new BebidasDoEventoId(idEvento, idBebida);
        Optional<BebidasDoEvento> existenteOpt = bebidasDoEventoRepository.findById(id);

        if (existenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BebidasDoEvento relacao = existenteOpt.get();
        relacao.setDestaque(false);
        relacao.setPrecoPromocional(null);
        bebidasDoEventoRepository.save(relacao);

        return ResponseEntity.noContent().build();
    }
}
