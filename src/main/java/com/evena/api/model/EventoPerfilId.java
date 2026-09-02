package com.evena.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EventoPerfilId implements Serializable {

    @Column(name = "Evento_id_eve")
    private Integer eventoId;

    @Column(name = "Perfil_id_perf")
    private Integer perfilId;

    public EventoPerfilId() {
    }

    public EventoPerfilId(Integer eventoId, Integer perfilId) {
        this.eventoId = eventoId;
        this.perfilId = perfilId;
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public Integer getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Integer perfilId) {
        this.perfilId = perfilId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventoPerfilId that)) {
            return false;
        }
        return Objects.equals(eventoId, that.eventoId)
                && Objects.equals(perfilId, that.perfilId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventoId, perfilId);
    }
}

