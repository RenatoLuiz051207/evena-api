package com.evena.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Localizacao")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loca")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Evento_id_eve", nullable = false)
    private Evento evento;

    @Column(name = "lat_loca", precision = 11, scale = 8)
    private BigDecimal latitude;

    @Column(name = "long_loca", precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(name = "endereco_loca", length = 100)
    private String endereco;

    @Column(name = "uf_loca", length = 10)
    private String uf;

    @Column(name = "cep_loca", length = 15)
    private String cep;

    @Column(name = "cidade_loca", length = 50)
    private String cidade;

    @Column(name = "nome_estabelecimento_loca", length = 100)
    private String nomeEstabelecimento;

    public Localizacao() {
    }

    public void atualizarDados(BigDecimal latitude, BigDecimal longitude, String endereco,
                               String uf, String cep, String cidade, String nomeEstabelecimento) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.endereco = endereco;
        this.uf = uf;
        this.cep = cep;
        this.cidade = cidade;
        this.nomeEstabelecimento = nomeEstabelecimento;
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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }
}
