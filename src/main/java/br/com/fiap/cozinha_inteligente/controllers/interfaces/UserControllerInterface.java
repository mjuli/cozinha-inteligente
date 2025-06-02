package br.com.fiap.cozinha_inteligente.controllers.interfaces;

import br.com.fiap.cozinha_inteligente.dtos.UpdatePasswordRequestDTO;
import br.com.fiap.cozinha_inteligente.dtos.UserInfoDTO;
import br.com.fiap.cozinha_inteligente.dtos.UserRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface UserControllerInterface {

  @Operation(
          description = "Cadastra um novo usuário no sistema",
          summary = "Cadastro de usuário",
          responses = {
                  @ApiResponse(
                          description = "Usuário cadastrado com sucesso",
                          responseCode = "201",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = br.com.fiap.cozinha_inteligente.commons.ApiResponse.class),
                                  examples = @ExampleObject(
                                          name = "Sucesso",
                                          value = """
                                                  {
                                                      "status": "sucesso",
                                                      "message": "Usuário cadastrado com sucesso",
                                                      "data": {
                                                          "id": 1,
                                                          "name": "João Silva",
                                                          "email": "joao@email.com",
                                                          "login": "joao",
                                                          "address": {
                                                              "street": "Rua das Flores",
                                                              "number": "123",
                                                              "city": "São Paulo"
                                                          },
                                                          "lastUpdatedAt": "2024-01-15T10:30:00"
                                                      },
                                                      "error": null
                                                  }
                                                  """
                                  )
                          )
                  ),
                  @ApiResponse(
                          description = "Usuário já existe",
                          responseCode = "400",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = br.com.fiap.cozinha_inteligente.commons.ApiResponse.class),
                                  examples = @ExampleObject(
                                          name = "Usuário duplicado",
                                          value = """
                                                  {
                                                      "status": "erro",
                                                      "message": "Usuário já cadastrado",
                                                      "data": null,
                                                      "error": "null"
                                                  }
                                                  """
                                  )
                          )
                  ),
                  @ApiResponse(
                          description = "Erro interno do servidor",
                          responseCode = "500",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = br.com.fiap.cozinha_inteligente.commons.ApiResponse.class)
                          )
                  )
          }
  )
  ResponseEntity<br.com.fiap.cozinha_inteligente.commons.ApiResponse<UserInfoDTO>> create(UserRequestDTO user, UriComponentsBuilder uriBuilder);

  @Operation(
          description = "Atualiza os dados de um usuário existente",
          summary = "Atualização de usuário",
          security = @SecurityRequirement(name = "bearer-key"),
          responses = {
                  @ApiResponse(
                          description = "Usuário atualizado com sucesso",
                          responseCode = "200",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = br.com.fiap.cozinha_inteligente.commons.ApiResponse.class),
                                  examples = @ExampleObject(
                                          name = "Sucesso",
                                          value = """
                                                  {
                                                      "status": "sucesso",
                                                      "message": "Usuário atualizado com sucesso",
                                                      "data": {
                                                          "id": 1,
                                                          "name": "João Silva Santos",
                                                          "email": "joao.santos@email.com",
                                                          "login": "joao",
                                                          "address": {
                                                                      "id": 8,
                                                                      "street": "Rua das Laranjeira",
                                                                      "number": "123",
                                                                      "complement": null,
                                                                      "neighborhood": "Centro",
                                                                      "city": "Natal",
                                                                      "state": "RN",
                                                                      "zipCode": "59070400",
                                                                      "country": "BR"
                                                                  },
                                                          "lastUpdatedAt": "2024-01-15T11:30:00"
                                                      },
                                                      "error": null
                                                  }
                                                  """
                                  )
                          )
                  ),
                  @ApiResponse(
                          description = "Usuário não encontrado",
                          responseCode = "404",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = br.com.fiap.cozinha_inteligente.commons.ApiResponse.class),
                                  examples = @ExampleObject(
                                          name = "Usuário não encontrado",
                                          value = """
                                                  {
                                                      "status": "erro",
                                                      "message": "Usuário não localizado",
                                                      "data": null,
                                                      "error": "null"
                                                  }
                                                  """
                                  )
                          )
                  ),
                  @ApiResponse(
                          description = "Não autorizado - Token inválido",
                          responseCode = "401"
                  ),
                  @ApiResponse(
                          description = "Erro interno do servidor",
                          responseCode = "500"
                  )
          }
  )
  ResponseEntity<br.com.fiap.cozinha_inteligente.commons.ApiResponse<UserInfoDTO>> update(UserRequestDTO user, String id);

  @Operation(
          description = "Remove um usuário do sistema",
          summary = "Remoção de usuário",
          security = @SecurityRequirement(name = "bearer-key"),
          responses = {
                  @ApiResponse(
                          description = "Usuário removido com sucesso",
                          responseCode = "200",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = br.com.fiap.cozinha_inteligente.commons.ApiResponse.class),
                                  examples = @ExampleObject(
                                          name = "Sucesso",
                                          value = """
                                                  {
                                                      "status": "sucesso",
                                                      "message": "Usuário removido com sucesso",
                                                      "data": null,
                                                      "error": null
                                                  }
                                                  """
                                  )
                          )
                  ),
                  @ApiResponse(
                          description = "Usuário não encontrado",
                          responseCode = "404",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = br.com.fiap.cozinha_inteligente.commons.ApiResponse.class)
                          )
                  ),
                  @ApiResponse(
                          description = "Não autorizado - Token inválido",
                          responseCode = "401"
                  ),
                  @ApiResponse(
                          description = "Acesso negado - Apenas ADMINs",
                          responseCode = "403"
                  ),
                  @ApiResponse(
                          description = "Erro interno do servidor",
                          responseCode = "500"
                  )
          }
  )
  ResponseEntity<br.com.fiap.cozinha_inteligente.commons.ApiResponse<UserInfoDTO>> delete(String id);

  @Operation(
          description = "Altera a senha de um usuário",
          summary = "Alteração de senha",
          security = @SecurityRequirement(name = "bearer-key"),
          responses = {
                  @ApiResponse(
                          description = "Senha alterada com sucesso",
                          responseCode = "200",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = br.com.fiap.cozinha_inteligente.commons.ApiResponse.class),
                                  examples = @ExampleObject(
                                          name = "Sucesso",
                                          value = """
                                                  {
                                                      "status": "sucesso",
                                                      "message": "Senha alterada com sucesso",
                                                      "data": null,
                                                      "error": null
                                                  }
                                                  """
                                  )
                          )
                  ),
                  @ApiResponse(
                          description = "Usuário não encontrado",
                          responseCode = "404",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = br.com.fiap.cozinha_inteligente.commons.ApiResponse.class)
                          )
                  ),
                  @ApiResponse(
                          description = "Senha atual incorreta",
                          responseCode = "400",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = br.com.fiap.cozinha_inteligente.commons.ApiResponse.class),
                                  examples = @ExampleObject(
                                          name = "Senha inválida",
                                          value = """
                                                  {
                                                      "status": "erro",
                                                      "message": "Senha atual incorreta",
                                                      "data": null,
                                                      "error": "null"
                                                  }
                                                  """
                                  )
                          )
                  ),
                  @ApiResponse(
                          description = "Não autorizado - Token inválido",
                          responseCode = "401"
                  ),
                  @ApiResponse(
                          description = "Erro interno do servidor",
                          responseCode = "500"
                  )
          }
  )
  ResponseEntity<br.com.fiap.cozinha_inteligente.commons.ApiResponse<Void>> updatePassword(String id, UpdatePasswordRequestDTO updatePassword);
}
