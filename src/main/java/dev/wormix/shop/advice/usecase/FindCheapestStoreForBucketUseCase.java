package dev.wormix.shop.advice.usecase;

import dev.wormix.shop.advice.dto.BestPriceStore;
import dev.wormix.shop.advice.dto.Bucket;
import dev.wormix.shop.advice.dto.Store;
import dev.wormix.shop.advice.usecase.command.CheckBasketPriceInStoreCommand;
import dev.wormix.shop.advice.usecase.command.FindCheapestStoreForBucketCommand;
import java.util.Comparator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FindCheapestStoreForBucketUseCase implements UseCase<FindCheapestStoreForBucketCommand, BestPriceStore> {

  @NotNull CheckBasketPriceInStoreUseCase checkBasketPrice;

  @Override
  @SuppressWarnings("OptionalGetWithoutIsPresent")
  public @NotNull BestPriceStore execute(@NotNull FindCheapestStoreForBucketCommand command) {
    Bucket bucket = new Bucket(command.getBucket().getName(), command.getBucket().getProductList());
    return command.getStoreList().stream()
        .map(store -> {
          double price = checkBasketPrice.execute(new CheckBasketPriceInStoreCommand(command.getBucket(), store));
          double latitude = store.getLocation().getY();
          double longitude = store.getLocation().getX();
          return new BestPriceStore(new Store(store.getId(), store.getName(), latitude, longitude), bucket, price);
        })
        .min(Comparator.comparingDouble(BestPriceStore::getPrice))
        .get();
  }
}
