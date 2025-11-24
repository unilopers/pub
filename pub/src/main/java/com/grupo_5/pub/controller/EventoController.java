package com.grupo_5.pub.controller;

import com.grupo_5.pub.model.Evento;
import com.grupo_5.pub.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Integer id) {
        Optional<Evento> evento = eventoRepository.findById(id);

        return evento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evento criar(@RequestBody Evento evento) {
        return eventoRepository.save(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable Integer id, @RequestBody Evento eventoAtualizado) {
        Optional<Evento> eventoExistente = eventoRepository.findById(id);

        if (eventoExistente.isPresent()) {
            Evento evento = eventoExistente.get();

            evento.setNome(eventoAtualizado.getNome());
            evento.setData(eventoAtualizado.getData());
            evento.setHoraInicio(eventoAtualizado.getHoraInicio());
            evento.setAtracoesMusicais(eventoAtualizado.getAtracoesMusicais());

            eventoRepository.save(evento);
            return ResponseEntity.ok(evento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Optional<Evento> evento = eventoRepository.findById(id);

        if (evento.isPresent()) {
            eventoRepository.delete(evento.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
