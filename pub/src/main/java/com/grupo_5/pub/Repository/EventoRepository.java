package com.grupo_5.pub.Repository;

import com.grupo_5.pub.Model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
