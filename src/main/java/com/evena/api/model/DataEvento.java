package com.evena.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DataEvento")
public class DataEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_data_evento")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Evento_id_eve", nullable = false)
    private Evento evento;

    @Column(name = "data_hora_evento")
    private LocalDateTime dataHora;

    public DataEvento() {
    }

    public DataEvento(Evento evento, LocalDateTime dataHora) {
        this.evento = evento;
        this.dataHora = dataHora;
    }

    public void alterarDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
