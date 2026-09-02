package com.evena.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Artista")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_art")
    private Integer id;

    @Column(name = "nome_art", length = 100)
    private String nome;

    @Column(name = "obras_art", length = 300)
    private String obras;

    @Column(name = "foto_art", length = 255)
    private String foto;

    public Artista() {
    }

    public void editarDados(String nome, String obras, String foto) {
        this.nome = nome;
        this.obras = obras;
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
