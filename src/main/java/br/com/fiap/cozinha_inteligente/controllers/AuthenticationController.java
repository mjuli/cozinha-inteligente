package br.com.fiap.cozinha_inteligente.controllers;

import br.com.fiap.cozinha_inteligente.dtos.AuthenticationDataDTO;
import br.com.fiap.cozinha_inteligente.dtos.TokenJWTDataDTO;
import br.com.fiap.cozinha_inteligente.entities.User;
import br.com.fiap.cozinha_inteligente.services.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class AuthenticationController {

  private final AuthenticationManager manager;

  private final TokenService tokenService;

  public AuthenticationController(AuthenticationManager manager, TokenService tokenService) {
    this.manager = manager;
    this.tokenService = tokenService;
  }

  @PostMapping
  public ResponseEntity login(@RequestBody @Valid AuthenticationDataDTO data) {
    try {
      var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
      Authentication authentication = manager.authenticate(authenticationToken);

      String tokenJWT = tokenService.getToken((User) authentication.getPrincipal());

      return ResponseEntity.ok(new TokenJWTDataDTO(tokenJWT));
    } catch (Exception e) {
      log.error("Erro ao realizar login({}): ", data.login(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
