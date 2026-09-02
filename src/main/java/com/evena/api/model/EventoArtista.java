package com.evena.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Evento_has_Artista")
public class EventoArtista {

    @EmbeddedId
    private EventoArtistaId id;

    @ManyToOne(optional = false)
    @MapsId("eventoId")
    @JoinColumn(name = "Evento_id_eve", nullable = false)
    private Evento evento;

    @ManyToOne(optional = false)
    @MapsId("artistaId")
    @JoinColumn(name = "Artista_id_art", nullable = false)
    private Artista artista;

    public EventoArtista() {
    }

    public EventoArtista(Evento evento, Artista artista) {
        this.evento = evento;
        this.artista = artista;
        this.id = new EventoArtistaId(evento.getId(), artista.getId());
    }

    public EventoArtistaId getId() {
        return id;
    }

    public void setId(EventoArtistaId id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}
