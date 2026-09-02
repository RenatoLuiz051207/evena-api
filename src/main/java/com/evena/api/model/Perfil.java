package com.evena.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perf")
    private Integer id;

    @Column(name = "nome_perf", length = 100)
    private String nome;

    @Column(name = "email_perf", length = 50)
    private String email;

    @JsonIgnore
    @Column(name = "senha_perf", length = 255)
    private String senha;

    @Column(name = "telefone_perf", length = 20)
    private String telefone;

    @Column(name = "data_criacao_perf")
    private LocalDate dataCriacao;

    @Column(name = "foto_perf", length = 255)
    private String foto;

    @Column(name = "banner_perf", length = 255)
    private String banner;

    @Column(name = "descricao_perf", length = 500)
    private String descricao;

    public Perfil() {
    }

    public Perfil(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = LocalDate.now();
    }

    public void editarPerfil(String nome, String telefone, String foto, String banner, String descricao) {
        this.nome = nome;
        this.telefone = telefone;
        this.foto = foto;
        this.banner = banner;
        this.descricao = descricao;
    }

    public void alterarSenha(String senha) {
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
