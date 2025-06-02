package br.com.fiap.cozinha_inteligente.controllers.interfaces;

import br.com.fiap.cozinha_inteligente.dtos.AuthenticationDataDTO;
import br.com.fiap.cozinha_inteligente.dtos.TokenJWTDataDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationControllerInterface {

  @Operation(
          description = "Realiza a autenticação do usuário no sistema",
          summary = "Login de usuário",
          responses = {
                  @ApiResponse(
                          description = "Login realizado com sucesso",
                          responseCode = "200",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = ApiResponse.class),
                                  examples = @ExampleObject(
                                          name = "Sucesso",
                                          value = """
                                                  {
                                                      "status": "sucesso",
                                                      "message": "Login realizado com sucesso",
                                                      "data": {
                                                          "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
                                                      },
                                                      "error": null
                                                  }
                                                  """
                                  )
                          )
                  ),
                  @ApiResponse(
                          description = "Credenciais inválidas",
                          responseCode = "400",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = ApiResponse.class),
                                  examples = @ExampleObject(
                                          name = "Erro de autenticação",
                                          value = """
                                                  {
                                                      "status": "erro",
                                                      "message": "Falha ao realizar login",
                                                      "data": null,
                                                      "error": "Usuário inexistente ou senha inválida"
                                                  }
                                                  """
                                  )
                          )
                  )
          }
  )
  ResponseEntity<br.com.fiap.cozinha_inteligente.commons.ApiResponse<TokenJWTDataDTO>> login(AuthenticationDataDTO data);
}
