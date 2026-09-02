package com.evena.api.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class DataEventoRequest {

    @NotNull(message = "Informe a data e hora.")
    private LocalDateTime dataHora;

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
