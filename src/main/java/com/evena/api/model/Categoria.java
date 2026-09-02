package com.evena.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Evento_id_eve", nullable = false)
    private Evento evento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Artista_id_art", nullable = false)
    private Artista artista;

    @Column(name = "tipo_cat", length = 50)
    private String tipo;

    @Column(name = "estilo_cat", length = 50)
    private String estilo;

    @Column(name = "foto_cat", length = 255)
    private String foto;

    public Categoria() {
    }

    public void editarDados(String tipo, String estilo, String foto) {
        this.tipo = tipo;
        this.estilo = estilo;
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
