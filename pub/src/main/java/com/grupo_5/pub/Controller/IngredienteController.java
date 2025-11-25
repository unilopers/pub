package com.grupo_5.pub.Controller;

import com.grupo_5.pub.Model.Ingrediente;
import com.grupo_5.pub.Repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/ingredientes", produces = "application/xml")
public class IngredienteController {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    // -------------------------
    // LISTAR TODOS
    // -------------------------
    @GetMapping
    public List<Ingrediente> listar() {
        return ingredienteRepository.findAll();
    }

    // -------------------------
    // BUSCAR POR ID
    // -------------------------
    @GetMapping("/{id}")
    public Ingrediente buscarPorId(@PathVariable int id) {
        Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id);
        return ingrediente.orElse(null);
    }

    // -------------------------
    // CRIAR INGREDIENTE
    // -------------------------
    @PostMapping
    public Ingrediente criar(@RequestBody Ingrediente ingrediente) {
        ingrediente.setId(0); // garante INSERT
        return ingredienteRepository.save(ingrediente);
    }

    // -------------------------
    // ATUALIZAR INGREDIENTE
    // -------------------------
    @PutMapping("/{id}")
    public Ingrediente atualizar(@PathVariable int id, @RequestBody Ingrediente dadosAtualizados) {

        Optional<Ingrediente> op = ingredienteRepository.findById(id);
        if (op.isEmpty()) {
            return null;
        }

        Ingrediente ingrediente = op.get();

        ingrediente.setNome(dadosAtualizados.getNome());
        ingrediente.setUnidadeMedida(dadosAtualizados.getUnidadeMedida());
        ingrediente.setEstoqueAtual(dadosAtualizados.getEstoqueAtual());
        ingrediente.setEstoqueMinimo(dadosAtualizados.getEstoqueMinimo());

        return ingredienteRepository.save(ingrediente);
    }

    // -------------------------
    // DELETAR INGREDIENTE
    // -------------------------
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable int id) {
        if (!ingredienteRepository.existsById(id)) {
            return "<msg>Ingrediente não encontrado</msg>";
        }

        ingredienteRepository.deleteById(id);
        return "<msg>Ingrediente removido com sucesso</msg>";
    }

    // -------------------------
    // ENTRADA DE ESTOQUE (COMPRA)
    // -------------------------
    @PostMapping("/{id}/entrada/{quantidade}")
    public Ingrediente entradaEstoque(@PathVariable int id, @PathVariable double quantidade) {

        Optional<Ingrediente> op = ingredienteRepository.findById(id);
        if (op.isEmpty()) return null;

        Ingrediente ingrediente = op.get();

        // REGRAS DE NEGÓCIO DENTRO DA CONTROLLER
        ingrediente.setEstoqueAtual(ingrediente.getEstoqueAtual() + quantidade);

        return ingredienteRepository.save(ingrediente);
    }

    // -------------------------
    // BAIXA DE ESTOQUE (CONSUMO)
    // -------------------------
    @PostMapping("/{id}/baixa/{quantidade}")
    public Ingrediente baixaEstoque(@PathVariable int id, @PathVariable double quantidade) {

        Optional<Ingrediente> op = ingredienteRepository.findById(id);
        if (op.isEmpty()) return null;

        Ingrediente ingrediente = op.get();

        // Regra de negócio: não pode ficar negativo
        if (ingrediente.getEstoqueAtual() - quantidade < 0) {
            ingrediente.setEstoqueAtual(0.0);
        } else {
            ingrediente.setEstoqueAtual(ingrediente.getEstoqueAtual() - quantidade);
        }

        return ingredienteRepository.save(ingrediente);
    }

    // -------------------------
    // LISTAR ESTOQUE BAIXO
    // -------------------------
    @GetMapping("/baixo-estoque")
    public List<Ingrediente> listarAbaixoDoMinimo() {
        return ingredienteRepository.findByEstoqueAtualLessThanEstoqueMinimo();
    }
}
