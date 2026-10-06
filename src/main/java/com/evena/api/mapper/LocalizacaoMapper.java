package com.evena.api.mapper;

import com.evena.api.dto.LocalizacaoRequest;
import com.evena.api.model.Localizacao;
import org.springframework.stereotype.Component;

@Component
public class LocalizacaoMapper {
    public Localizacao toEntity(LocalizacaoRequest request) {
        Localizacao localizacao = new Localizacao();

        localizacao.setLatitude(request.getLatitude());
        localizacao.setLongitude(request.getLongitude());
        localizacao.setEndereco(request.getEndereco());
        localizacao.setUf(request.getUf());
        localizacao.setCep(request.getCep());
        localizacao.setCidade(request.getCidade());
        localizacao.setNomeEstabelecimento(request.getNomeEstabelecimento());

        return localizacao;
    }
}