package br.com.fiap.cozinha_inteligente.dtos;

import br.com.fiap.cozinha_inteligente.entities.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
  @NotBlank
  @Schema(example = "João Silva")
  String name,
  @NotBlank
  @Email
  @Schema(example = "joao@email.com")
  String email,
  @NotBlank
  @Schema(example = "joao")
  String login,
  @NotBlank
  @Size(min = 6)
  @Schema(example = "password123")
  String password,
  @NotNull
  Address address
) {
}
