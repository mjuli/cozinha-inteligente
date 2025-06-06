package br.com.fiap.cozinha_inteligente.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi<T> {
  private String status;
  private String message;
  private T data;
  private Object metadata;
}
