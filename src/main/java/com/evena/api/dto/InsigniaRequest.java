package com.evena.api.dto;

import jakarta.validation.constraints.NotBlank;

public class InsigniaRequest {

    @NotBlank(message = "Digite o nome da insÃ­gnia.")
    private String nome;

    private String icone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }
}
