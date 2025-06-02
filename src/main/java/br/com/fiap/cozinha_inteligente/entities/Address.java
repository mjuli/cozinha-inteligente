package br.com.fiap.cozinha_inteligente.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "addresses")
@Entity(name = "Address")
@Data
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Schema(example = "Rua das Laranjeiras")
  private String street;
  @Schema(example = "123")
  private String number;
  @Schema(example = "Apt. 101")
  private String complement;
  @Schema(example = "Centro")
  private String neighborhood;
  @Schema(example = "Natal")
  private String city;
  @Schema(example = "RN")
  private String state;
  @Schema(example = "59070400")
  private String zipCode;
  @Schema(example = "BR")
  private String country;
}
