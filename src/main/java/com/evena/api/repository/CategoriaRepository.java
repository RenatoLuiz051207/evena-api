package com.evena.api.repository;

import com.evena.api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    List<Categoria> findByEventoId(Integer eventoId);

    void deleteByEventoId(Integer eventoId);
}
