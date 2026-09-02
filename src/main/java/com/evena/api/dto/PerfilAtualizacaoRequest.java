package com.evena.api.dto;

import jakarta.validation.constraints.NotBlank;

public class PerfilAtualizacaoRequest {

    @NotBlank(message = "Digite seu nome.")
    private String nome;

    private String telefone;
    private String foto;
    private String banner;
    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
