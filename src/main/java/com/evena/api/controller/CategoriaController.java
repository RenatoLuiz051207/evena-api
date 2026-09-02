package com.evena.api.controller;

import com.evena.api.dto.CategoriaRequest;
import com.evena.api.dto.MensagemResponse;
import com.evena.api.model.Categoria;
import com.evena.api.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listar();
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(@Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public Categoria editar(@PathVariable Integer id,
                            @Valid @RequestBody CategoriaRequest request) {
        return categoriaService.editar(id, request);
    }

    @DeleteMapping("/{id}")
    public MensagemResponse remover(@PathVariable Integer id) {
        categoriaService.remover(id);
        return new MensagemResponse("Categoria removida com sucesso.");
    }
}
