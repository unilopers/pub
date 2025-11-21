package com.grupo_5.pub.Repository;

import com.grupo_5.pub.Model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
}
