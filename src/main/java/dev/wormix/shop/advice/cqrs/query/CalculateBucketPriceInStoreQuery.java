package dev.wormix.shop.advice.cqrs.query;

import dev.wormix.shop.advice.model.BucketModel;
import dev.wormix.shop.advice.model.StoreModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CalculateBucketPriceInStoreQuery {

  @NotNull StoreModel storeModel;

  @NotNull BucketModel bucketModel;
}
