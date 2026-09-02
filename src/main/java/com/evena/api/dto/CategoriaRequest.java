package com.evena.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoriaRequest {

    @NotNull(message = "Informe o evento.")
    private Integer eventoId;

    @NotNull(message = "Informe o artista.")
    private Integer artistaId;

    @NotBlank(message = "Digite o tipo da categoria.")
    private String tipo;

    private String estilo;
    private String foto;

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public Integer getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(Integer artistaId) {
        this.artistaId = artistaId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
