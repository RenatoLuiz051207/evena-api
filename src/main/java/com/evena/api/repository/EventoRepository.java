package com.evena.api.repository;

import com.evena.api.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

    List<Evento> findByStatusTrueOrderByIdDesc();

    List<Evento> findByEmpresaIdOrderByIdDesc(Integer empresaId);
}
