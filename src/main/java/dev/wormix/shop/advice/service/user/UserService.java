package dev.wormix.shop.advice.service.user;

import dev.wormix.shop.advice.model.UserModel;
import org.jetbrains.annotations.NotNull;

public interface UserService {

  @NotNull UserModel getById(long id);
}
