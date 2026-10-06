package com.evena.api.mapper;

import com.evena.api.dto.ArtistaRequest;
import com.evena.api.model.Artista;
import org.springframework.stereotype.Component;

@Component
public class ArtistaMapper {

    public Artista toEntity(ArtistaRequest request) {
        Artista artista = new Artista();

        artista.setNome(request.getNome());
        artista.setObras(request.getObras());
        artista.setFoto(request.getFoto());

        return artista;
    }
}