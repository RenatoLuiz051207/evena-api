package com.evena.api.service;

import com.evena.api.dto.CategoriaRequest;
import com.evena.api.exception.EntidadeNaoEncontradaException;
import com.evena.api.model.Artista;
import com.evena.api.model.Categoria;
import com.evena.api.model.Evento;
import com.evena.api.repository.ArtistaRepository;
import com.evena.api.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ArtistaRepository artistaRepository;
    private final EventoService eventoService;

    public CategoriaService(CategoriaRepository categoriaRepository,
                            ArtistaRepository artistaRepository,
                            EventoService eventoService) {
        this.categoriaRepository = categoriaRepository;
        this.artistaRepository = artistaRepository;
        this.eventoService = eventoService;
    }

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Transactional
    public Categoria cadastrar(CategoriaRequest request) {
        Evento evento = eventoService.buscarEntidade(request.getEventoId());
        Artista artista = artistaRepository.findById(request.getArtistaId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Artista nÃ£o encontrado."));

        Categoria categoria = new Categoria();
        categoria.setEvento(evento);
        categoria.setArtista(artista);
        categoria.editarDados(request.getTipo().trim(), request.getEstilo(), request.getFoto());

        return categoriaRepository.save(categoria);
    }

    @Transactional
    public Categoria editar(Integer id, CategoriaRequest request) {
        Categoria categoria = buscarEntidade(id);
        categoria.editarDados(request.getTipo().trim(), request.getEstilo(), request.getFoto());
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void remover(Integer id) {
        categoriaRepository.delete(buscarEntidade(id));
    }

    private Categoria buscarEntidade(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria nÃ£o encontrada."));
    }
}
