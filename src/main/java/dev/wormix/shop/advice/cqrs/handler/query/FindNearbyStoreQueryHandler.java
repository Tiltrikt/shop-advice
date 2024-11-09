package dev.wormix.shop.advice.cqrs.handler.query;

import dev.crashbandicootfm.mediator.model.CommandHandler;
import dev.wormix.shop.advice.api.Bucket;
import dev.wormix.shop.advice.api.Store;
import dev.wormix.shop.advice.api.StoreBucketPrice;
import dev.wormix.shop.advice.cqrs.query.CalculateBucketPriceInStoreQuery;
import dev.wormix.shop.advice.cqrs.query.FindNearbyStoreQuery;
import dev.wormix.shop.advice.exception.ProductNotFoundException;
import dev.wormix.shop.advice.model.BucketModel;
import dev.wormix.shop.advice.model.StoreModel;
import dev.wormix.shop.advice.repository.StoreRepository;
import dev.wormix.shop.advice.service.bucket.BucketService;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FindNearbyStoreQueryHandler implements CommandHandler<FindNearbyStoreQuery, List<StoreBucketPrice>> {

  @NotNull StoreRepository storeRepository;

  @NotNull BucketService bucketService;

  @NotNull CommandHandler<CalculateBucketPriceInStoreQuery, Double> calculateBucketPriceInStoreCommandHandler;
  private final CalculateBucketPriceInStoreQueryHandler calculateBucketPriceInStoreQueryHandler;

  @Override
  public @NotNull List<StoreBucketPrice> handle(@NotNull FindNearbyStoreQuery command) {
    List<StoreModel> storeList = storeRepository.findByLocationNear(command.getLongitude(), command.getLatitude(), 2000);
    BucketModel bucketModel = bucketService.getById(command.getBucketId());
    List<StoreBucketPrice> storeBucketPrices = storeList.stream()
        .map(storeModel -> {
          CalculateBucketPriceInStoreQuery calculateBucketPriceInStoreQuery = new CalculateBucketPriceInStoreQuery(
              storeModel,
              bucketModel
          );
          Bucket bucket = new Bucket(bucketModel.getId(), bucketModel.getName(), bucketModel.getProductList());
          double price;
          try {
            price = calculateBucketPriceInStoreQueryHandler.handle(calculateBucketPriceInStoreQuery);
          } catch (ProductNotFoundException e) {
            return null;
          }
          double latitude = storeModel.getLocation().getY();
          double longitude = storeModel.getLocation().getX();
          Store store = new Store(storeModel.getId(), storeModel.getName(), latitude, longitude);
          return new StoreBucketPrice(store, bucket, price);
        })
        .filter(Objects::nonNull)
        .sorted(Comparator.comparingDouble(StoreBucketPrice::getPrice))
        .limit(5)
        .collect(Collectors.toList());
    return storeBucketPrices;
  }
}
