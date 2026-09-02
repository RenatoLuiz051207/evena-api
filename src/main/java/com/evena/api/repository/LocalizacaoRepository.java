package com.evena.api.repository;

import com.evena.api.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Integer> {

    List<Localizacao> findByEventoId(Integer eventoId);

    List<Localizacao> findByCidadeContainingIgnoreCase(String cidade);

    void deleteByEventoId(Integer eventoId);
}
