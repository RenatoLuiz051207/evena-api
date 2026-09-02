package com.evena.api.controller;

import com.evena.api.dto.InsigniaRequest;
import com.evena.api.dto.MensagemResponse;
import com.evena.api.dto.MissaoRequest;
import com.evena.api.model.Insignia;
import com.evena.api.model.PerfilInsignia;
import com.evena.api.service.InsigniaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InsigniaController {

    private final InsigniaService insigniaService;

    public InsigniaController(InsigniaService insigniaService) {
        this.insigniaService = insigniaService;
    }

    @GetMapping("/insignias")
    public List<Insignia> listar() {
        return insigniaService.listar();
    }

    @PostMapping("/insignias")
    public ResponseEntity<Insignia> cadastrar(@Valid @RequestBody InsigniaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(insigniaService.cadastrar(request));
    }

    @PutMapping("/insignias/{id}")
    public Insignia editar(@PathVariable Integer id,
                           @Valid @RequestBody InsigniaRequest request) {
        return insigniaService.editar(id, request);
    }

    @DeleteMapping("/insignias/{id}")
    public MensagemResponse remover(@PathVariable Integer id) {
        insigniaService.remover(id);
        return new MensagemResponse("InsÃ­gnia removida com sucesso.");
    }

    @PostMapping("/perfis/{perfilId}/insignias/{insigniaId}")
    public PerfilInsignia atribuir(@PathVariable Integer perfilId,
                                   @PathVariable Integer insigniaId,
                                   @RequestBody(required = false) MissaoRequest request) {
        String missao = request == null ? null : request.getMissao();
        return insigniaService.atribuir(perfilId, insigniaId, missao);
    }

    @PatchMapping("/perfis/{perfilId}/insignias/{insigniaId}/concluir")
    public PerfilInsignia concluirMissao(@PathVariable Integer perfilId,
                                         @PathVariable Integer insigniaId) {
        return insigniaService.concluirMissao(perfilId, insigniaId);
    }

    @GetMapping("/perfis/{perfilId}/insignias")
    public List<PerfilInsignia> listarDoPerfil(@PathVariable Integer perfilId) {
        return insigniaService.listarDoPerfil(perfilId);
    }
}
