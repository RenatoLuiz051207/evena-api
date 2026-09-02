package com.evena.api.repository;

import com.evena.api.model.PerfilInsignia;
import com.evena.api.model.PerfilInsigniaId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PerfilInsigniaRepository extends JpaRepository<PerfilInsignia, PerfilInsigniaId> {

    List<PerfilInsignia> findByPerfilId(Integer perfilId);
}
