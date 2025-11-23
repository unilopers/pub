// Exemplo de PromocaoRepository (necessário para o Controller)
package com.grupo_5.pub.Repository;

import com.grupo_5.pub.Model.PromocaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromocaoRepository extends JpaRepository<PromocaoModel, Integer> {
    // Método derivado para buscar promoções onde a data de fim ainda não passou
    List<PromocaoModel> findByDataFimAfter(LocalDate data);
}