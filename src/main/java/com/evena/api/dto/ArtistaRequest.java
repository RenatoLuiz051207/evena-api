package com.evena.api.dto;

import jakarta.validation.constraints.NotBlank;

public class ArtistaRequest {

    @NotBlank(message = "Digite o nome do artista.")
    private String nome;

    private String obras;
    private String foto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObras() {
        return obras;
    }

    public void setObras(String obras) {
        this.obras = obras;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
