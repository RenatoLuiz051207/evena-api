package com.evena.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmpresaRequest {

    @NotNull(message = "Informe o perfil responsÃ¡vel.")
    private Integer perfilId;

    private String cnpj;

    @NotBlank(message = "Digite o nome da empresa.")
    private String nome;

    private String endereco;
    private String setor;

    public Integer getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Integer perfilId) {
        this.perfilId = perfilId;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}
