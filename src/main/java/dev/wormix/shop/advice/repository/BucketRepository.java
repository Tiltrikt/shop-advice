package dev.wormix.shop.advice.repository;

import dev.wormix.shop.advice.model.BucketModel;
import dev.wormix.shop.advice.model.UserModel;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<BucketModel, Long> {

  @NotNull List<BucketModel> findAllByUser(@NotNull UserModel userId);
}
