package dev.wormix.shop.advice.service.recommendation;

import dev.wormix.shop.advice.model.StoreRecommendationModel;
import org.jetbrains.annotations.NotNull;

public interface StoreRecommendationService {

  @NotNull StoreRecommendationModel getByUserId(long userId);

  @NotNull StoreRecommendationModel save(@NotNull StoreRecommendationModel storeRecommendationModel);
}
