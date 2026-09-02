package com.evena.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoResponse {

    private Integer id;
    private String titulo;
    private Boolean status;
    private String classificacao;
    private String banner;
    private String capa;
    private String descricao;
    private BigDecimal preco;
    private String link;
    private Integer empresaId;
    private String empresaNome;
    private List<LocalDateTime> datas = new ArrayList<>();
    private String categoria;
    private String local;
    private String endereco;
    private String cidade;
    private String uf;
    private List<String> artistas = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaNome() {
        return empresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }

    public List<LocalDateTime> getDatas() {
        return datas;
    }

    public void setDatas(List<LocalDateTime> datas) {
        this.datas = datas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<String> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<String> artistas) {
        this.artistas = artistas;
    }
}
