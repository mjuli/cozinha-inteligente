package br.com.fiap.cozinha_inteligente.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record TokenJWTDataDTO (
        @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJDb3ppbmhhSW5...")
        String token
) {
}
