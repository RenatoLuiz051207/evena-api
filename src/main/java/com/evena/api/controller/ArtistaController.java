package com.evena.api.controller;

import com.evena.api.dto.ArtistaRequest;
import com.evena.api.dto.MensagemResponse;
import com.evena.api.model.Artista;
import com.evena.api.service.ArtistaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistaController {

    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping("/artistas")
    public List<Artista> listar() {
        return artistaService.listar();
    }

    @GetMapping("/artistas/{id}")
    public Artista buscar(@PathVariable Integer id) {
        return artistaService.buscar(id);
    }

    @PostMapping("/artistas")
    public ResponseEntity<Artista> cadastrar(@Valid @RequestBody ArtistaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(artistaService.cadastrar(request));
    }

    @PutMapping("/artistas/{id}")
    public Artista editarDados(@PathVariable Integer id,
                               @Valid @RequestBody ArtistaRequest request) {
        return artistaService.editarDados(id, request);
    }

    @DeleteMapping("/artistas/{id}")
    public MensagemResponse remover(@PathVariable Integer id) {
        artistaService.remover(id);
        return new MensagemResponse("Artista removido com sucesso.");
    }

    @PostMapping("/eventos/{eventoId}/artistas/{artistaId}")
    public MensagemResponse adicionarArtista(@PathVariable Integer eventoId,
                                             @PathVariable Integer artistaId) {
        artistaService.adicionarArtista(eventoId, artistaId);
        return new MensagemResponse("Artista adicionado ao evento.");
    }

    @DeleteMapping("/eventos/{eventoId}/artistas/{artistaId}")
    public MensagemResponse removerArtista(@PathVariable Integer eventoId,
                                           @PathVariable Integer artistaId) {
        artistaService.removerArtista(eventoId, artistaId);
        return new MensagemResponse("Artista removido do evento.");
    }
}
