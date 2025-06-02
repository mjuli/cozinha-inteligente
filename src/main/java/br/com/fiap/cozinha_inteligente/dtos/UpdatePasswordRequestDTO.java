package br.com.fiap.cozinha_inteligente.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordRequestDTO(
        @NotBlank String currentPassword,
        @NotBlank @Size(min = 6) String newPassword
) {
}
