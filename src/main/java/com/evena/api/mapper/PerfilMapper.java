package com.evena.api.mapper;

import com.evena.api.dto.PerfilAtualizacaoRequest;
import com.evena.api.dto.PerfilCadastroRequest;
import com.evena.api.dto.PerfilResponse;
import com.evena.api.model.Perfil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PerfilMapper {
    public PerfilResponse toResponse(Perfil perfil) {
        return new PerfilResponse(perfil);
    }

    public Perfil toEntity(PerfilCadastroRequest request) {
        Perfil perfil = new Perfil();

        perfil.setNome(request.getNome());
        perfil.setEmail(request.getEmail());
        perfil.setSenha(request.getSenha());

        return perfil;
    }

    public void updateEntity(Perfil perfil, PerfilAtualizacaoRequest request) {

        perfil.setNome(request.getNome());
        perfil.setEmail(request.getEmail());
        perfil.setTelefone(request.getTelefone());
        perfil.setFoto(request.getFoto());
        perfil.setBanner(request.getBanner());
        perfil.setDescricao(request.getDescricao());
    }
}