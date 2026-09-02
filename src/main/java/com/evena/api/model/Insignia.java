package com.evena.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Insignias")
public class Insignia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ins")
    private Integer id;

    @Column(name = "nome_ins", length = 35)
    private String nome;

    @Column(name = "icone_ins", length = 255)
    private String icone;

    public Insignia() {
    }

    public void editarDados(String nome, String icone) {
        this.nome = nome;
        this.icone = icone;
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

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }
}
