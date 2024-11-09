package dev.wormix.shop.advice.cqrs.handler.query;

import dev.crashbandicootfm.mediator.model.CommandHandler;
import dev.wormix.shop.advice.api.Bucket;
import dev.wormix.shop.advice.api.Store;
import dev.wormix.shop.advice.api.StoreBucketPrice;
import dev.wormix.shop.advice.cqrs.query.CalculateBucketPriceInStoreQuery;
import dev.wormix.shop.advice.cqrs.query.FindNearbyStoreQuery;
import dev.wormix.shop.advice.exception.StoreNotFoundException;
import dev.wormix.shop.advice.model.BucketModel;
import dev.wormix.shop.advice.model.StoreModel;
import dev.wormix.shop.advice.repository.BucketRepository;
import dev.wormix.shop.advice.repository.StoreRepository;
import dev.wormix.shop.advice.service.bucket.BucketService;
import java.util.Comparator;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FindNearbyStoreQueryHandler implements CommandHandler<FindNearbyStoreQuery, StoreBucketPrice> {

  @NotNull StoreRepository storeRepository;

  @NotNull BucketService bucketService;

  @NotNull CommandHandler<CalculateBucketPriceInStoreQuery, Double> calculateBucketPriceInStoreCommandHandler;
  private final CalculateBucketPriceInStoreQueryHandler calculateBucketPriceInStoreQueryHandler;

  @Override
  @SuppressWarnings("OptionalGetWithoutIsPresent")
  public @NotNull StoreBucketPrice handle(@NotNull FindNearbyStoreQuery command) {
    List<StoreModel> storeList = storeRepository.findByLocationNear(command.getLongitude(), command.getLatitude(), 500);
    BucketModel bucketModel = bucketService.getById(command.getBucketId());
    return storeList.stream()
        .map(storeModel -> {
          System.out.println("Sdsafdsfsdfds");
          CalculateBucketPriceInStoreQuery calculateBucketPriceInStoreQuery = new CalculateBucketPriceInStoreQuery(
              storeModel,
              bucketModel
          );
          Bucket bucket = new Bucket(bucketModel.getName(), bucketModel.getProductList());
          double price = calculateBucketPriceInStoreQueryHandler.handle(calculateBucketPriceInStoreQuery);
          double latitude = storeModel.getLocation().getY();
          double longitude = storeModel.getLocation().getX();
          Store store = new Store(storeModel.getId(), storeModel.getName(), latitude, longitude);
          return new StoreBucketPrice(store, bucket, price);
        })
        .min(Comparator.comparingDouble(StoreBucketPrice::getPrice))
        .orElseThrow(() -> new StoreNotFoundException("No nearby store found"));
  }
}
