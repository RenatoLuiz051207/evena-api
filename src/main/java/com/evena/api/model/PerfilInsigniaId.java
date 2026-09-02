package com.evena.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PerfilInsigniaId implements Serializable {

    @Column(name = "Perfil_id_perf")
    private Integer perfilId;

    @Column(name = "Insignias_id_ins")
    private Integer insigniaId;

    public PerfilInsigniaId() {
    }

    public PerfilInsigniaId(Integer perfilId, Integer insigniaId) {
        this.perfilId = perfilId;
        this.insigniaId = insigniaId;
    }

    public Integer getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Integer perfilId) {
        this.perfilId = perfilId;
    }

    public Integer getInsigniaId() {
        return insigniaId;
    }

    public void setInsigniaId(Integer insigniaId) {
        this.insigniaId = insigniaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PerfilInsigniaId that)) {
            return false;
        }
        return Objects.equals(perfilId, that.perfilId)
                && Objects.equals(insigniaId, that.insigniaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(perfilId, insigniaId);
    }
}

