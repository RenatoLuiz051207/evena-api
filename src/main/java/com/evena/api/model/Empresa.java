package com.evena.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emp")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Perfil_id_perf", nullable = false)
    private Perfil perfil;

    @Column(name = "cnpj_emp", length = 18)
    private String cnpj;

    @Column(name = "nome_emp", length = 100)
    private String nome;

    @Column(name = "endereco_emp", length = 200)
    private String endereco;

    @Column(name = "setor_emp", length = 50)
    private String setor;

    public Empresa() {
    }

    public void editarDados(String cnpj, String nome, String endereco, String setor) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.setor = setor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}
