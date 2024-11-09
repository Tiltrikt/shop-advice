package dev.wormix.shop.advice.usecase;

import dev.wormix.shop.advice.model.StoreRecommendationModel;
import dev.wormix.shop.advice.repository.StoreRecommendationRepository;
import dev.wormix.shop.advice.usecase.command.GetBestStoreForDayCommand;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetBestStoreForDayUseCase implements UseCase<GetBestStoreForDayCommand, StoreRecommendationModel> {

  @NotNull StoreRecommendationRepository storeRecommendationRepository;

  @Override
  public @NotNull StoreRecommendationModel execute(@NotNull GetBestStoreForDayCommand command) {
    return storeRecommendationRepository.findById(command.getUserId()).get();
  }
}
