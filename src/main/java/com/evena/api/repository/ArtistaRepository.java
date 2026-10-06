package com.evena.api.repository;

import com.evena.api.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    List<Artista> findByNome(String nome);
}