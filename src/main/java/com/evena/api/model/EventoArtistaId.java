package com.evena.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EventoArtistaId implements Serializable {

    @Column(name = "Evento_id_eve")
    private Integer eventoId;

    @Column(name = "Artista_id_art")
    private Integer artistaId;

    public EventoArtistaId() {
    }

    public EventoArtistaId(Integer eventoId, Integer artistaId) {
        this.eventoId = eventoId;
        this.artistaId = artistaId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventoArtistaId that)) {
            return false;
        }
        return Objects.equals(eventoId, that.eventoId)
                && Objects.equals(artistaId, that.artistaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventoId, artistaId);
    }
}

