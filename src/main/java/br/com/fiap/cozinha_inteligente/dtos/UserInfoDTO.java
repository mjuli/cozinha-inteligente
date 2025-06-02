package br.com.fiap.cozinha_inteligente.dtos;

import br.com.fiap.cozinha_inteligente.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
  private Long id;
  private String name;
  private String email;
  private String login;
  private Address address;
  private Date lastUpdatedAt;
}
