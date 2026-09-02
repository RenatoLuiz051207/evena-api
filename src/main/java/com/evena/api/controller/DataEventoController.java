package com.evena.api.controller;

import com.evena.api.dto.DataEventoRequest;
import com.evena.api.dto.MensagemResponse;
import com.evena.api.model.DataEvento;
import com.evena.api.service.DataEventoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DataEventoController {

    private final DataEventoService dataEventoService;

    public DataEventoController(DataEventoService dataEventoService) {
        this.dataEventoService = dataEventoService;
    }

    @PostMapping("/eventos/{eventoId}/datas")
    public ResponseEntity<DataEvento> adicionarData(@PathVariable Integer eventoId,
                                                    @Valid @RequestBody DataEventoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dataEventoService.adicionarData(eventoId, request));
    }

    @PutMapping("/datas-evento/{id}")
    public DataEvento alterarDataHora(@PathVariable Integer id,
                                      @Valid @RequestBody DataEventoRequest request) {
        return dataEventoService.alterarDataHora(id, request);
    }

    @DeleteMapping("/datas-evento/{id}")
    public MensagemResponse removerData(@PathVariable Integer id) {
        dataEventoService.removerData(id);
        return new MensagemResponse("Data removida com sucesso.");
    }

    @GetMapping("/eventos/{eventoId}/datas")
    public List<DataEvento> listarDatas(@PathVariable Integer eventoId) {
        return dataEventoService.listarDatas(eventoId);
    }
}
