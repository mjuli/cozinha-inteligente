package br.com.fiap.cozinha_inteligente.dtos;

import br.com.fiap.cozinha_inteligente.entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
  @NotBlank
  String name,
  @NotBlank
  @Email
  String email,
  @NotBlank
  String login,
  @NotBlank
  @Size(min = 6)
  String password,
  @NotNull
  Address address
) {
}
