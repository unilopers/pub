package com.grupo_5.pub.Repository;

import com.grupo_5.pub.Model.PromocaoAplicada;
import com.grupo_5.pub.Model.PromocaoAplicadaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromocaoAplicadaRepository extends JpaRepository<PromocaoAplicada, PromocaoAplicadaId> {
}

