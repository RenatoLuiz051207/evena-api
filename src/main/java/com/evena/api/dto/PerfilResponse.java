package com.evena.api.dto;

import com.evena.api.model.Perfil;
import java.time.LocalDate;

public class PerfilResponse {

    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataCriacao;
    private String foto;
    private String banner;
    private String descricao;

    public PerfilResponse() {
    }

    public PerfilResponse(Perfil perfil) {
        this.id = perfil.getId();
        this.nome = perfil.getNome();
        this.email = perfil.getEmail();
        this.telefone = perfil.getTelefone();
        this.dataCriacao = perfil.getDataCriacao();
        this.foto = perfil.getFoto();
        this.banner = perfil.getBanner();
        this.descricao = perfil.getDescricao();
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
