package com.educational.grs_api.v1.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CriacaoReservaDTO(@NotNull Long salaId,
                                @NotNull Long usuarioId,
                                @NotNull LocalDateTime dataInicio,
                                @NotNull LocalDateTime dataFim) {
}
