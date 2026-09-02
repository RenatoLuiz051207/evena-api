package com.evena.api.service;

import com.evena.api.dto.ArtistaRequest;
import com.evena.api.exception.EntidadeNaoEncontradaException;
import com.evena.api.model.Artista;
import com.evena.api.model.Evento;
import com.evena.api.model.EventoArtista;
import com.evena.api.model.EventoArtistaId;
import com.evena.api.repository.ArtistaRepository;
import com.evena.api.repository.EventoArtistaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;
    private final EventoArtistaRepository eventoArtistaRepository;
    private final EventoService eventoService;

    public ArtistaService(ArtistaRepository artistaRepository,
                          EventoArtistaRepository eventoArtistaRepository,
                          EventoService eventoService) {
        this.artistaRepository = artistaRepository;
        this.eventoArtistaRepository = eventoArtistaRepository;
        this.eventoService = eventoService;
    }

    public List<Artista> listar() {
        return artistaRepository.findAll();
    }

    public Artista buscar(Integer id) {
        return buscarEntidade(id);
    }

    @Transactional
    public Artista cadastrar(ArtistaRequest request) {
        Artista artista = new Artista();
        artista.editarDados(request.getNome().trim(), request.getObras(), request.getFoto());
        return artistaRepository.save(artista);
    }

    @Transactional
    public Artista editarDados(Integer id, ArtistaRequest request) {
        Artista artista = buscarEntidade(id);
        artista.editarDados(request.getNome().trim(), request.getObras(), request.getFoto());
        return artistaRepository.save(artista);
    }

    @Transactional
    public void remover(Integer id) {
        artistaRepository.delete(buscarEntidade(id));
    }

    @Transactional
    public void adicionarArtista(Integer eventoId, Integer artistaId) {
        Evento evento = eventoService.buscarEntidade(eventoId);
        Artista artista = buscarEntidade(artistaId);
        EventoArtistaId id = new EventoArtistaId(eventoId, artistaId);

        if (!eventoArtistaRepository.existsById(id)) {
            eventoArtistaRepository.save(new EventoArtista(evento, artista));
        }
    }

    @Transactional
    public void removerArtista(Integer eventoId, Integer artistaId) {
        eventoArtistaRepository.deleteById(new EventoArtistaId(eventoId, artistaId));
    }

    private Artista buscarEntidade(Integer id) {
        return artistaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Artista nÃ£o encontrado."));
    }
}
