package br.com.fiap.cozinha_inteligente.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthenticationDataDTO(
        @Schema(example = "user-login")
        String login,
        @Schema(example = "password123")
        String password) {
}
