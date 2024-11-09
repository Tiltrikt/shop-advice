package dev.wormix.shop.advice.service.bucket;

import dev.wormix.shop.advice.api.Bucket;
import dev.wormix.shop.advice.model.BucketModel;
import org.jetbrains.annotations.NotNull;

public interface BucketService {

  @NotNull BucketModel getById(long id);
}
