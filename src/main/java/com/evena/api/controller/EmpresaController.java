package com.evena.api.controller;

import com.evena.api.dto.EmpresaRequest;
import com.evena.api.dto.EventoRequest;
import com.evena.api.dto.EventoResponse;
import com.evena.api.dto.MensagemResponse;
import com.evena.api.model.Empresa;
import com.evena.api.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public List<Empresa> listar() {
        return empresaService.listar();
    }

    @PostMapping
    public ResponseEntity<Empresa> cadastrar(@Valid @RequestBody EmpresaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(empresaService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public Empresa editar(@PathVariable Integer id,
                          @Valid @RequestBody EmpresaRequest request) {
        return empresaService.editar(id, request);
    }

    @DeleteMapping("/{id}")
    public MensagemResponse remover(@PathVariable Integer id) {
        empresaService.remover(id);
        return new MensagemResponse("Empresa removida com sucesso.");
    }

    @PostMapping("/{empresaId}/eventos")
    public ResponseEntity<EventoResponse> cadastrarEvento(@PathVariable Integer empresaId,
                                                          @Valid @RequestBody EventoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(empresaService.cadastrarEvento(empresaId, request));
    }

    @PutMapping("/eventos/{eventoId}")
    public EventoResponse editarEvento(@PathVariable Integer eventoId,
                                       @Valid @RequestBody EventoRequest request) {
        return empresaService.editarEvento(eventoId, request);
    }

    @DeleteMapping("/eventos/{eventoId}")
    public MensagemResponse removerEvento(@PathVariable Integer eventoId) {
        empresaService.removerEvento(eventoId);
        return new MensagemResponse("Evento removido com sucesso.");
    }
}
