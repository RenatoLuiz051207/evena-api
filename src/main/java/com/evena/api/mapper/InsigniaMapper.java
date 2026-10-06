package com.evena.api.mapper;

import com.evena.api.dto.InsigniaRequest;
import com.evena.api.model.Insignia;
import org.springframework.stereotype.Component;

@Component
public class InsigniaMapper {
    public Insignia toEntity(InsigniaRequest request) {
        Insignia insignia = new Insignia();

        insignia.setNome(request.getNome());
        insignia.setIcone(request.getIcone());

        return insignia;
    }
}