package dev.wormix.shop.advice.service.bucket;

import dev.wormix.shop.advice.model.BucketModel;
import dev.wormix.shop.advice.model.UserModel;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

public interface BucketService {

  @NotNull BucketModel getById(long id);

  @Unmodifiable
  @NotNull List<BucketModel> getAllByUser(@NotNull UserModel user);

  @NotNull BucketModel save(@NotNull BucketModel bucket);
}
