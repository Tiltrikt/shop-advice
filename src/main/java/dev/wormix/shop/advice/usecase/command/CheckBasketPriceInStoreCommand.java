package dev.wormix.shop.advice.usecase.command;

import dev.wormix.shop.advice.model.BucketModel;
import dev.wormix.shop.advice.model.StoreModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckBasketPriceInStoreCommand {

  @NotNull BucketModel bucketModel;

  @NotNull StoreModel storeModel;
}
