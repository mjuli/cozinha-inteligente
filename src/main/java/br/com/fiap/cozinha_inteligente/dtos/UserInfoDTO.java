package br.com.fiap.cozinha_inteligente.dtos;

import br.com.fiap.cozinha_inteligente.entities.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
  @Schema(example = "01")
  private Long id;
  @Schema(example = "João Silva")
  private String name;
  @Schema(example = "joao@email.com")
  private String email;
  @Schema(example = "joao")
  private String login;
  private Address address;
  @Schema(example = "2025-01-01T03:00:00.000Z")
  private Date lastUpdatedAt;
}
