package com.evena.api.repository;

import com.evena.api.model.DataEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DataEventoRepository extends JpaRepository<DataEvento, Integer> {

    List<DataEvento> findByEventoIdOrderByDataHoraAsc(Integer eventoId);

    void deleteByEventoId(Integer eventoId);
}
