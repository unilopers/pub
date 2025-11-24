package com.grupo_5.pub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo_5.pub.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
