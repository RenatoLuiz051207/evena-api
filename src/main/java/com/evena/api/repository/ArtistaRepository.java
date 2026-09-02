package com.evena.api.repository;

import com.evena.api.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {
}
