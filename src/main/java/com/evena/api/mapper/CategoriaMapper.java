package com.evena.api.mapper;

import com.evena.api.dto.CategoriaRequest;
import com.evena.api.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequest request) {
        Categoria categoria = new Categoria();

        categoria.setTipo(request.getTipo());
        categoria.setEstilo(request.getEstilo());
        categoria.setFoto(request.getFoto());

        return categoria;
    }
}