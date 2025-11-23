package com.grupo_5.pub.Controller;

import com.grupo_5.pub.Model.PromocaoModel;
import com.grupo_5.pub.Repository.PromocaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/promocoes", produces = MediaType.APPLICATION_XML_VALUE)
public class PromocaoController {

    private final PromocaoRepository promocaoRepository;

    public PromocaoController(PromocaoRepository promocaoRepository) {
        this.promocaoRepository = promocaoRepository;
    }


    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<PromocaoModel> criarPromocao(@RequestBody PromocaoModel novaPromocao) {
        PromocaoModel salva = promocaoRepository.save(novaPromocao);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }


    @GetMapping
    public ResponseEntity<List<PromocaoModel>> listarTodas() {
        List<PromocaoModel> promocoes = promocaoRepository.findAll();
        return ResponseEntity.ok(promocoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromocaoModel> buscarPorId(@PathVariable Integer id) {
        Optional<PromocaoModel> promocaoOpt = promocaoRepository.findById(id);
        return promocaoOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/ativas")
    public ResponseEntity<List<PromocaoModel>> listarAtivas() {
        List<PromocaoModel> ativas = promocaoRepository.findByDataFimAfter(LocalDate.now());
        return ResponseEntity.ok(ativas);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<PromocaoModel> atualizarPromocao(@PathVariable Integer id,
                                                           @RequestBody PromocaoModel atualizada) {
        Optional<PromocaoModel> promocaoOpt = promocaoRepository.findById(id);
        if (promocaoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PromocaoModel existente = promocaoOpt.get();

        existente.setNome(atualizada.getNome());
        existente.setDescricao(atualizada.getDescricao());
        existente.setDataInicio(atualizada.getDataInicio());
        existente.setDataFim(atualizada.getDataFim());
        existente.setTipoDesconto(atualizada.getTipoDesconto());
        existente.setValorDesconto(atualizada.getValorDesconto());

        PromocaoModel salva = promocaoRepository.save(existente);
        return ResponseEntity.ok(salva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPromocao(@PathVariable Integer id) {
        if (!promocaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        promocaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}