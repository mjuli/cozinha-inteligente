package br.com.fiap.cozinha_inteligente.entities;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "addresses")
@Entity(name = "Address")
@Data
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String street;
  private String number;
  private String complement;
  private String neighborhood;
  private String city;
  private String state;
  private String zipCode;
  private String country;
}
