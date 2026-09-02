package com.evena.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Perfil_has_Evento")
public class EventoPerfil {

    @EmbeddedId
    private EventoPerfilId id;

    @ManyToOne(optional = false)
    @MapsId("eventoId")
    @JoinColumn(name = "Evento_id_eve", nullable = false)
    private Evento evento;

    @ManyToOne(optional = false)
    @MapsId("perfilId")
    @JoinColumn(name = "Perfil_id_perf", nullable = false)
    private Perfil perfil;

    public EventoPerfil() {
    }

    public EventoPerfil(Evento evento, Perfil perfil) {
        this.evento = evento;
        this.perfil = perfil;
        this.id = new EventoPerfilId(evento.getId(), perfil.getId());
    }

    public EventoPerfilId getId() {
        return id;
    }

    public void setId(EventoPerfilId id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
