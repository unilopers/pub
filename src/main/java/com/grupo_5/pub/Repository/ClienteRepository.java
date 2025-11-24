package com.grupo_5.pub.Repository;

import com.grupo_5.pub.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
