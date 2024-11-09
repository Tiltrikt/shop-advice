package dev.wormix.shop.advice.repository;

import dev.wormix.shop.advice.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
