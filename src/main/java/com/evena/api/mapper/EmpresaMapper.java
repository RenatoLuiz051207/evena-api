package com.evena.api.mapper;

import com.evena.api.dto.EmpresaRequest;
import com.evena.api.model.Empresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {
    public Empresa toEntity(EmpresaRequest request) {
        Empresa empresa = new Empresa();

        empresa.setCnpj(request.getCnpj());
        empresa.setNome(request.getNome());
        empresa.setEndereco(request.getEndereco());
        empresa.setSetor(request.getSetor());

        return empresa;
    }
}