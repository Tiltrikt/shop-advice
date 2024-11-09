package dev.wormix.shop.advice.repository;

import dev.wormix.shop.advice.model.StoreRecommendationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRecommendationRepository extends JpaRepository<StoreRecommendationModel, Long> {
}
