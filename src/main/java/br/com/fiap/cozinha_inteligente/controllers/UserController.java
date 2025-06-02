package br.com.fiap.cozinha_inteligente.controllers;

import br.com.fiap.cozinha_inteligente.commons.ApiResponse;
import br.com.fiap.cozinha_inteligente.controllers.interfaces.UserControllerInterface;
import br.com.fiap.cozinha_inteligente.dtos.UpdatePasswordRequestDTO;
import br.com.fiap.cozinha_inteligente.dtos.UserInfoDTO;
import br.com.fiap.cozinha_inteligente.dtos.UserRequestDTO;
import br.com.fiap.cozinha_inteligente.entities.User;
import br.com.fiap.cozinha_inteligente.exceptions.InvalidPasswordException;
import br.com.fiap.cozinha_inteligente.exceptions.UserAlreadyExistsException;
import br.com.fiap.cozinha_inteligente.exceptions.UserNotFoundException;
import br.com.fiap.cozinha_inteligente.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController implements UserControllerInterface {

  private final UserService userService;

  private final ModelMapper modelMapper;

  public UserController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @PostMapping
  public ResponseEntity<ApiResponse<UserInfoDTO>> create(@RequestBody @Valid UserRequestDTO user, UriComponentsBuilder uriBuilder) {
    try {
      User userCreated = userService.create(user);

      ApiResponse<UserInfoDTO> response = new ApiResponse<>(
              "sucesso", "Usuário cadastrado com sucesso", userToUserInfoDTO(userCreated), null);

      var uri = uriBuilder.path("/user/{id}").buildAndExpand(userCreated.getId()).toUri();

      return ResponseEntity.created(uri).body(response);
    } catch (UserAlreadyExistsException e) {
      log.error("Usuário já cadastrado: ", e);

      ApiResponse<UserInfoDTO> response = new ApiResponse<>(
              "erro", "Usuário já cadastrado", null, e.getMessage());

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    } catch (Exception e) {
      log.error("Erro inesperado ao cadastrar usuário: ", e);

      ApiResponse<UserInfoDTO> response = new ApiResponse<>(
              "erro", "Não foi possível cadastrar usuário", null, e.getMessage());

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<UserInfoDTO>> update(@RequestBody @Valid UserRequestDTO user, @PathVariable String id) {
    try {
      User userUpdated = userService.update(id, user);

      ApiResponse<UserInfoDTO> response = new ApiResponse<>(
              "sucesso", "Usuário atualizado com sucesso", userToUserInfoDTO(userUpdated), null);

      return ResponseEntity.ok(response);
    } catch (UserNotFoundException e) {
      log.warn("Usuário não encontrado - id: {}, message: {}", id, e.getMessage());

      ApiResponse<UserInfoDTO> response = new ApiResponse<>(
              "erro", "Usuário não localizado", null, e.getMessage());

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    } catch (Exception e) {
      log.error("Erro inesperado ao atualizar usuário - id: {}", id, e);

      ApiResponse<UserInfoDTO> response = new ApiResponse<>(
              "erro", "Não foi possível atualizar usuário", null, e.getMessage());

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<UserInfoDTO>> delete(@PathVariable String id) {
    try {
      userService.delete(id);

      ApiResponse<UserInfoDTO> response = new ApiResponse<>(
              "sucesso", "Usuário removido com sucesso", null, null);

      return ResponseEntity.ok(response);
    } catch (UserNotFoundException e) {
      log.warn("Usuário não encontrado - id: {}, message: {}", id, e.getMessage());

      ApiResponse<UserInfoDTO> response = new ApiResponse<>(
              "erro", "Usuário não localizado", null, e.getMessage());

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    } catch (Exception e) {
      log.error("Erro inesperado ao remover usuário - id: {}", id, e);

      ApiResponse<UserInfoDTO> response = new ApiResponse<>(
              "erro", "Não foi possível remover o usuário", null, e.getMessage());

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @PatchMapping("/{id}/password")
  public ResponseEntity<ApiResponse<Void>> updatePassword(@PathVariable String id, @RequestBody @Valid UpdatePasswordRequestDTO updatePassword) {
    try {
      userService.changePassword(id, updatePassword);

      ApiResponse<Void> response = new ApiResponse<>(
              "sucesso", "Senha alterada com sucesso", null, null);

      return ResponseEntity.ok(response);
    } catch (UserNotFoundException e) {
      log.warn("Usuário não encontrado - id: {}, message: {}", id, e.getMessage());

      ApiResponse<Void> response = new ApiResponse<>(
              "erro", "Usuário não localizado", null, e.getMessage());

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    } catch (InvalidPasswordException e) {
      log.warn("Senha atual inválida - id: {}", id);

      ApiResponse<Void> response = new ApiResponse<>(
              "erro", "Senha atual incorreta", null, e.getMessage());

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    } catch (Exception e) {
      log.error("Erro inesperado ao alterar senha - id: {}", id, e);

      ApiResponse<Void> response = new ApiResponse<>(
              "erro", "Não foi possível alterar a senha", null, e.getMessage());

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  private UserInfoDTO userToUserInfoDTO(User user) {
    return modelMapper.map(user, UserInfoDTO.class);
  }
}

