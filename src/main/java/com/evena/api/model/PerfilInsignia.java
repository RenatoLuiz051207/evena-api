package com.evena.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Perfil_has_Insignias")
public class PerfilInsignia {

    @EmbeddedId
    private PerfilInsigniaId id;

    @ManyToOne(optional = false)
    @MapsId("perfilId")
    @JoinColumn(name = "Perfil_id_perf", nullable = false)
    private Perfil perfil;

    @ManyToOne(optional = false)
    @MapsId("insigniaId")
    @JoinColumn(name = "Insignias_id_ins", nullable = false)
    private Insignia insignia;

    @Column(name = "missao_ins_perf", length = 50)
    private String missao;

    public PerfilInsignia() {
    }

    public PerfilInsignia(Perfil perfil, Insignia insignia, String missao) {
        this.perfil = perfil;
        this.insignia = insignia;
        this.missao = missao;
        this.id = new PerfilInsigniaId(perfil.getId(), insignia.getId());
    }

    public void concluirMissao() {
        this.missao = "Concluída";
    }

    public PerfilInsigniaId getId() {
        return id;
    }

    public void setId(PerfilInsigniaId id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Insignia getInsignia() {
        return insignia;
    }

    public void setInsignia(Insignia insignia) {
        this.insignia = insignia;
    }

    public String getMissao() {
        return missao;
    }

    public void setMissao(String missao) {
        this.missao = missao;
    }
}
