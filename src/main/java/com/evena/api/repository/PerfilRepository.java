package com.evena.api.repository;

import com.evena.api.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

    Optional<Perfil> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);
}
