package com.evena.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RecuperarSenhaRequest {

    @NotBlank(message = "Digite seu e-mail.")
    @Email(message = "Digite um e-mail vÃ¡lido.")
    private String email;

    @NotBlank(message = "Digite a nova senha.")
    @Size(min = 8, message = "Use pelo menos 8 caracteres.")
    @Pattern(regexp = ".*[A-Z].*", message = "Adicione pelo menos uma letra maiÃºscula.")
    @Pattern(regexp = ".*[a-z].*", message = "Adicione pelo menos uma letra minÃºscula.")
    @Pattern(regexp = ".*[0-9].*", message = "Adicione pelo menos um nÃºmero.")
    private String novaSenha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
}
