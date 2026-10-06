package com.evena.api.mapper;

import com.evena.api.dto.EventoRequest;
import com.evena.api.dto.EventoResponse;
import com.evena.api.model.Categoria;
import com.evena.api.model.Evento;
import com.evena.api.model.Localizacao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class EventoMapper {

    public Evento toEntity(EventoRequest request) {
        Evento evento = new Evento();
        evento.setTitulo(request.getTitulo());
        evento.setStatus(request.getStatus());
        evento.setClassificacao(request.getClassificacao());
        evento.setBanner(request.getBanner());
        evento.setCapa(request.getCapa());
        evento.setDescricao(request.getDescricao());
        evento.setPreco(request.getPreco());
        evento.setLink(request.getLink());
        return evento;
    }

    public EventoResponse toResponse(Evento evento,
                                     List<LocalDateTime> datas,
                                     Categoria categoria,
                                     Localizacao localizacao,
                                     List<String> artistas) {
        EventoResponse response = new EventoResponse();
        response.setId(evento.getId());
        response.setTitulo(evento.getTitulo());
        response.setStatus(evento.getStatus());
        response.setClassificacao(evento.getClassificacao());
        response.setBanner(evento.getBanner());
        response.setCapa(evento.getCapa());
        response.setDescricao(evento.getDescricao());
        response.setPreco(evento.getPreco());
        response.setLink(evento.getLink());

        if (evento.getEmpresa() != null) {
            response.setEmpresaId(evento.getEmpresa().getId());
            response.setEmpresaNome(evento.getEmpresa().getNome());
        }

        response.setDatas(datas);

        if (categoria != null) {
            response.setCategoria(categoria.getTipo());
        }

        if (localizacao != null) {
            response.setLocal(localizacao.getNomeEstabelecimento());
            response.setEndereco(localizacao.getEndereco());
            response.setCidade(localizacao.getCidade());
            response.setUf(localizacao.getUf());
            response.setLatitude(localizacao.getLatitude());
            response.setLongitude(localizacao.getLongitude());
        }

        response.setArtistas(artistas);
        return response;
    }
}
