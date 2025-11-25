package com.grupo_5.pub.Repository;

import com.grupo_5.pub.Model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {

    // Mantendo a funcionalidade de verificar estoque baixo da branch 'estoque-leo'
    @Query("SELECT i FROM Ingrediente i WHERE i.estoqueAtual < i.estoqueMinimo")
    List<Ingrediente> findByEstoqueAtualLessThanEstoqueMinimo();
}