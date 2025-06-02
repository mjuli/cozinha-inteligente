package br.com.fiap.cozinha_inteligente.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordRequestDTO(
        @NotBlank
        @Schema(example = "password123")
        String currentPassword,
        @NotBlank
        @Size(min = 6)
        @Schema(example = "password1234")
        String newPassword
) {
}
