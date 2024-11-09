package dev.wormix.shop.advice.service.recommendation;

import dev.wormix.shop.advice.exception.UserNotHaveRecommendation;
import dev.wormix.shop.advice.model.StoreRecommendationModel;
import dev.wormix.shop.advice.repository.StoreRecommendationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StoreRecommendationServiceImpl implements StoreRecommendationService {

  @NotNull StoreRecommendationRepository repository;

  @Override
  public @NotNull StoreRecommendationModel getByUserId(long userId) {
    return repository.findById(userId)
        .orElseThrow(() -> new UserNotHaveRecommendation("User with id '%d' doesn't have any recommendations"));
  }

  @Override
  public @NotNull StoreRecommendationModel save(@NotNull StoreRecommendationModel storeRecommendationModel) {
    return repository.save(storeRecommendationModel);
  }
}
