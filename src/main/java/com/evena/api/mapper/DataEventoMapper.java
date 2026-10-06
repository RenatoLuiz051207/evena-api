package com.evena.api.mapper;

import com.evena.api.dto.DataEventoRequest;
import com.evena.api.model.DataEvento;
import org.springframework.stereotype.Component;

@Component
public class DataEventoMapper {
    public DataEvento toEntity(DataEventoRequest request) {

        DataEvento dataEvento = new DataEvento();

        dataEvento.setDataHora(request.getDataHora());

        return dataEvento;
    }
}