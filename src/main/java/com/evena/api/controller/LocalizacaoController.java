package com.evena.api.controller;

import com.evena.api.dto.LocalizacaoRequest;
import com.evena.api.dto.MensagemResponse;
import com.evena.api.model.Localizacao;
import com.evena.api.service.LocalizacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/localizacoes")
public class LocalizacaoController {

    private final LocalizacaoService localizacaoService;

    public LocalizacaoController(LocalizacaoService localizacaoService) {
        this.localizacaoService = localizacaoService;
    }

    @GetMapping
    public List<Localizacao> listar() {
        return localizacaoService.listar();
    }

    @GetMapping("/buscar")
    public List<Localizacao> buscarLocalizacao(@RequestParam(required = false) String cidade) {
        return localizacaoService.buscarLocalizacao(cidade);
    }

    @PostMapping
    public ResponseEntity<Localizacao> cadastrar(@Valid @RequestBody LocalizacaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(localizacaoService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public Localizacao editar(@PathVariable Integer id,
                              @Valid @RequestBody LocalizacaoRequest request) {
        return localizacaoService.editar(id, request);
    }

    @DeleteMapping("/{id}")
    public MensagemResponse remover(@PathVariable Integer id) {
        localizacaoService.remover(id);
        return new MensagemResponse("Localização removida com sucesso.");
    }
}
