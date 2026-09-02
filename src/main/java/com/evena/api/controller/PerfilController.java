package com.evena.api.controller;

import com.evena.api.dto.*;
import com.evena.api.service.PerfilService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfis")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PerfilResponse> cadastrar(@Valid @RequestBody PerfilCadastroRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(perfilService.cadastrar(request));
    }

    @PostMapping("/autenticar")
    public PerfilResponse autenticar(@Valid @RequestBody LoginRequest request) {
        return perfilService.autenticar(request);
    }

    @GetMapping("/{id}")
    public PerfilResponse buscar(@PathVariable Integer id) {
        return perfilService.buscar(id);
    }

    @PutMapping("/{id}")
    public PerfilResponse editarPerfil(@PathVariable Integer id,
                                       @Valid @RequestBody PerfilAtualizacaoRequest request) {
        return perfilService.editarPerfil(id, request);
    }

    @PostMapping("/recuperar-senha")
    public MensagemResponse recuperarSenha(@Valid @RequestBody RecuperarSenhaRequest request) {
        perfilService.recuperarSenha(request);
        return new MensagemResponse("Senha alterada com sucesso.");
    }

    @PostMapping("/{perfilId}/favoritos/{eventoId}")
    public MensagemResponse adicionarFavorito(@PathVariable Integer perfilId,
                                               @PathVariable Integer eventoId) {
        perfilService.adicionarFavorito(perfilId, eventoId);
        return new MensagemResponse("Evento adicionado aos favoritos.");
    }

    @DeleteMapping("/{perfilId}/favoritos/{eventoId}")
    public MensagemResponse removerFavorito(@PathVariable Integer perfilId,
                                             @PathVariable Integer eventoId) {
        perfilService.removerFavorito(perfilId, eventoId);
        return new MensagemResponse("Evento removido dos favoritos.");
    }

    @GetMapping("/{perfilId}/favoritos")
    public List<EventoResponse> listarFavoritos(@PathVariable Integer perfilId) {
        return perfilService.listarFavoritos(perfilId);
    }
}
