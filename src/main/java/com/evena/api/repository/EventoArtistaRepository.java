package com.evena.api.repository;

import com.evena.api.model.EventoArtista;
import com.evena.api.model.EventoArtistaId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoArtistaRepository extends JpaRepository<EventoArtista, EventoArtistaId> {

    List<EventoArtista> findByEventoId(Integer eventoId);

    void deleteByEventoId(Integer eventoId);
}
