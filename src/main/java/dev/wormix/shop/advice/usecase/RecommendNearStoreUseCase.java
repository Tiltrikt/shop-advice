//package dev.wormix.shop.advice.usecase;
//
//import dev.wormix.shop.advice.api.StoreBucketPrice;
//import dev.wormix.shop.advice.model.BucketModel;
//import dev.wormix.shop.advice.model.StoreModel;
//import dev.wormix.shop.advice.usecase.command.FindCheapestStoreForBucketCommand;
//import dev.wormix.shop.advice.usecase.command.FindNearestStoreCommand;
//import dev.wormix.shop.advice.usecase.command.GenerateStoreRecommendationCommand;
//import java.util.ArrayList;
//import java.util.List;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class RecommendNearStoreUseCase implements UseCase<GenerateStoreRecommendationCommand, StoreBucketPrice> {
//
//  @NotNull FindNearestStoresUseCase findNearestStores;
//
//  @NotNull FindCheapestStoreForBucketUseCase findCheapestStore;
//
//  @Override
//  public @NotNull StoreBucketPrice execute(@NotNull GenerateStoreRecommendationCommand command) {
//    BucketModel bucket = new BucketModel(1, "test", new ArrayList<>());
//    FindNearestStoreCommand findNearestStoreCommand = new FindNearestStoreCommand(
//        50,
//        command.getLatitude(),
//        command.getLongitude()
//    );
//    List<StoreModel> storeList = findNearestStores.execute(findNearestStoreCommand);
//    FindCheapestStoreForBucketCommand findCheapestStoreForBucketCommand = new FindCheapestStoreForBucketCommand(
//        storeList,
//        bucket
//    );
//    return findCheapestStore.execute(findCheapestStoreForBucketCommand);
//  }
//}
