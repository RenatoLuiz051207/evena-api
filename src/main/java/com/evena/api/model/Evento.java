package com.evena.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eve")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Empresa_id_emp", nullable = false)
    private Empresa empresa;

    @Column(name = "titulo_eve", length = 150)
    private String titulo;

    @Column(name = "status_eve")
    private Boolean status;

    @Column(name = "classificacao_eve", length = 10)
    private String classificacao;

    @Column(name = "banner_eve", length = 255)
    private String banner;

    @Column(name = "capa_eve", length = 255)
    private String capa;

    @Lob
    @Column(name = "descricao_eve", nullable = false)
    private String descricao;

    @Column(name = "preco_eve", precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "link_eve", length = 500)
    private String link;

    public Evento() {
    }

    public void ativar() {
        this.status = true;
    }

    public void desativar() {
        this.status = false;
    }

    public void editarDados(String titulo, String classificacao, String banner, String capa,
                            String descricao, BigDecimal preco, String link) {
        this.titulo = titulo;
        this.classificacao = classificacao;
        this.banner = banner;
        this.capa = capa;
        this.descricao = descricao;
        this.preco = preco;
        this.link = link;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
