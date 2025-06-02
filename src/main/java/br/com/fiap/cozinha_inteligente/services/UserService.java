package br.com.fiap.cozinha_inteligente.services;

import br.com.fiap.cozinha_inteligente.dtos.UpdatePasswordRequestDTO;
import br.com.fiap.cozinha_inteligente.dtos.UserRequestDTO;
import br.com.fiap.cozinha_inteligente.entities.User;
import br.com.fiap.cozinha_inteligente.exceptions.InvalidPasswordException;
import br.com.fiap.cozinha_inteligente.exceptions.UserAlreadyExistsException;
import br.com.fiap.cozinha_inteligente.exceptions.UserNotFoundException;
import br.com.fiap.cozinha_inteligente.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class UserService {

  private final UserRepository userRepository;

  private final ModelMapper modelMapper;

  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public User create(UserRequestDTO userInput) throws UserAlreadyExistsException {
    UserDetails userLogin = userRepository.findByLogin(userInput.login());

    if (Objects.nonNull(userLogin)) {
      throw new UserAlreadyExistsException("Usuário já cadastrado, login: " + userLogin.getUsername());
    }

    User userEmail = userRepository.findByEmail(userInput.email());

    if (Objects.nonNull(userEmail)) {
      throw new UserAlreadyExistsException("Usuário já cadastrado, email: " + userEmail.getEmail());
    }

    User user = modelMapper.map(userInput, User.class);
    user.setPassword(passwordEncoder.encode(userInput.password()));
    user.setCreatedAt(new Date());
    user.setLastUpdatedAt(new Date());

    return userRepository.save(user);
  }

  @Transactional
  public User update(String id, UserRequestDTO userInput) throws UserNotFoundException {
    User user = getById(id);

    if (Objects.isNull(user)) {
      throw new UserNotFoundException("Usuário não encontrado, id: " + id);
    }

    modelMapper.map(userInput, user);
    user.setPassword(passwordEncoder.encode(userInput.password()));
    user.setLastUpdatedAt(new Date());

    return userRepository.save(user);
  }

  @Transactional
  public void delete(String id) throws UserNotFoundException {
    User user = getById(id);

    if (Objects.isNull(user)) {
      throw new UserNotFoundException("Usuário não encontrado, id: " + id);
    }

    userRepository.delete(user);
  }

  @Transactional
  public void changePassword(String userId, UpdatePasswordRequestDTO data) throws UserNotFoundException, InvalidPasswordException {
    User user = getById(userId);

    if (Objects.isNull(user)) {
      throw new UserNotFoundException("Usuário não encontrado, id: " + userId);
    }

    if (!passwordEncoder.matches(data.currentPassword(), user.getPassword())) {
      throw new InvalidPasswordException("Senha atual incorreta");
    }

    if (passwordEncoder.matches(data.newPassword(), user.getPassword())) {
      throw new InvalidPasswordException("A nova senha deve ser diferente da senha atual");
    }

    user.setPassword(passwordEncoder.encode(data.newPassword()));
    user.setLastUpdatedAt(new Date());

    userRepository.save(user);
  }

  private User getById(String id) {
    Long userId = Long.valueOf(id);

    return userRepository.findById(userId).orElse(null);
  }
}
