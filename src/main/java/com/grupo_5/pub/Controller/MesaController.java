package com.grupo_5.pub.Controller;

import com.grupo_5.pub.Model.Mesa;
import com.grupo_5.pub.Model.Mesas;
import com.grupo_5.pub.Repository.MesaRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mesas", produces = MediaType.APPLICATION_XML_VALUE)
public class MesaController {

    private final MesaRepository mesaRepository;

    public MesaController(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public Mesas listar() {
        return new Mesas(mesaRepository.findAll());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public Mesa buscarPorId(@PathVariable Integer id) {
        return mesaRepository.findById(id).orElseThrow();
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Mesa criar(@RequestBody Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public Mesa atualizar(@PathVariable Integer id, @RequestBody Mesa mesaAtualizada) {
        Mesa mesa = mesaRepository.findById(id).orElseThrow();

        mesa.setCapacidade(mesaAtualizada.getCapacidade());
        mesa.setStatus(mesaAtualizada.getStatus());

        return mesaRepository.save(mesa);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        mesaRepository.deleteById(id);
    }
}
