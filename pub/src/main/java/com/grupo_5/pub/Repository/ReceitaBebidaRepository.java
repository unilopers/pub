package com.grupo_5.pub.Repository;

import com.grupo_5.pub.Model.ReceitaBebida;
import com.grupo_5.pub.Model.ReceitaBebidaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaBebidaRepository extends JpaRepository<ReceitaBebida, ReceitaBebidaId> {
}
