package com.coutodev.transacao_api.controller.Dtos;

import java.time.OffsetDateTime;

public record TransacaoRequestDto(Double valor , OffsetDateTime dataHora) {
}
