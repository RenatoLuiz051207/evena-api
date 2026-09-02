package com.evena.api.repository;

import com.evena.api.model.EventoPerfil;
import com.evena.api.model.EventoPerfilId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoPerfilRepository extends JpaRepository<EventoPerfil, EventoPerfilId> {

    List<EventoPerfil> findByPerfilId(Integer perfilId);

    void deleteByEventoId(Integer eventoId);
}
