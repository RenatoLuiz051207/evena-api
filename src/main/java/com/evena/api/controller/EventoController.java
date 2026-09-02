package com.evena.api.controller;

import com.evena.api.dto.EventoRequest;
import com.evena.api.dto.EventoResponse;
import com.evena.api.dto.MensagemResponse;
import com.evena.api.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<EventoResponse> listar() {
        return eventoService.listar();
    }

    @GetMapping("/ativos")
    public List<EventoResponse> listarAtivos() {
        return eventoService.listarAtivos();
    }

    @GetMapping("/pesquisar")
    public List<EventoResponse> pesquisar(@RequestParam(required = false) String termo) {
        return eventoService.pesquisar(termo);
    }

    @GetMapping("/{id}")
    public EventoResponse buscar(@PathVariable Integer id) {
        return eventoService.buscar(id);
    }

    @PostMapping
    public ResponseEntity<EventoResponse> cadastrar(@RequestParam Integer empresaId,
                                                    @Valid @RequestBody EventoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventoService.cadastrar(empresaId, request));
    }

    @PutMapping("/{id}")
    public EventoResponse editar(@PathVariable Integer id,
                                 @Valid @RequestBody EventoRequest request) {
        return eventoService.editar(id, request);
    }

    @PatchMapping("/{id}/ativar")
    public EventoResponse ativar(@PathVariable Integer id) {
        return eventoService.ativar(id);
    }

    @PatchMapping("/{id}/desativar")
    public EventoResponse desativar(@PathVariable Integer id) {
        return eventoService.desativar(id);
    }

    @DeleteMapping("/{id}")
    public MensagemResponse remover(@PathVariable Integer id) {
        eventoService.remover(id);
        return new MensagemResponse("Evento removido com sucesso.");
    }
}
