package dev.wormix.shop.advice.service.user;

import dev.wormix.shop.advice.exception.UserNotFoundException;
import dev.wormix.shop.advice.model.UserModel;
import dev.wormix.shop.advice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

  @NotNull UserRepository userRepository;

  @Override
  public @NotNull UserModel getById(long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id '%d' not found".formatted(id)));
  }
}
