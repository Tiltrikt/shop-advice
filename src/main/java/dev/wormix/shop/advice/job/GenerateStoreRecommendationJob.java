//package dev.wormix.shop.advice.job;
//
//import dev.wormix.shop.advice.api.StoreBucketPrice;
//import dev.wormix.shop.advice.model.BucketModel;
//import dev.wormix.shop.advice.model.StoreModel;
//import dev.wormix.shop.advice.model.StoreRecommendationModel;
//import dev.wormix.shop.advice.model.UserModel;
//import dev.wormix.shop.advice.repository.StoreRecommendationRepository;
//import dev.wormix.shop.advice.repository.StoreRepository;
//import dev.wormix.shop.advice.repository.UserRepository;
//import dev.wormix.shop.advice.usecase.CheckBasketPriceInStoreUseCase;
//import dev.wormix.shop.advice.usecase.command.FindCheapestStoreForBucketCommand;
//import java.util.ArrayList;
//import java.util.List;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class GenerateStoreRecommendationJob {
//
//  @NotNull UserRepository userRepository;
//
//  @NotNull StoreRepository storeRepository;
//
//  @NotNull StoreRecommendationRepository storeRecommendationRepository;
//
//  @NotNull CheckBasketPriceInStoreUseCase checkBasketPrice;
//
//  @NotNull FindCheapestStoreForBucketUseCase findCheapestStore;
//
//  @NotNull BucketModel bucket = new BucketModel(1, "test", new ArrayList<>());
//
//  @Async
//  @Scheduled(cron = "0 0 0 * * ?")
//  public void execute() {
//    storeRecommendationRepository.deleteAll();
//    int pageSize = 100;
//    int pageNumber = 0;
//
//    Page<UserModel> usersPage;
//
//    do {
//      usersPage = userRepository.findAll(PageRequest.of(pageNumber, pageSize));
//      List<StoreRecommendationModel> storeRecommendationList = new ArrayList<>();
//      for (UserModel user : usersPage.getContent()) {
//
//        List<StoreModel> storeList = storeRepository.findAllByCity(user.getCity());
//        FindCheapestStoreForBucketCommand fIndCheapestStoreForBucketCommand = new FindCheapestStoreForBucketCommand(
//            storeList,
//            bucket
//        );
//        StoreBucketPrice bestStore = findCheapestStore.execute(fIndCheapestStoreForBucketCommand);
//
//        StoreRecommendationModel storeRecommendationModel = new StoreRecommendationModel(
//            user.getId(),
//            bestStore.getStore().getId(),
//            bucket.getId(),
//            bestStore.getPrice()
//        );
//        storeRecommendationList.add(storeRecommendationModel);
//      }
//      storeRecommendationRepository.saveAll(storeRecommendationList);
//      pageNumber++;
//    } while (!usersPage.isLast());
//  }
//}