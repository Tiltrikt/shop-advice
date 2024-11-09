package dev.wormix.shop.advice.repository;

import dev.wormix.shop.advice.model.BucketModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<BucketModel, Long> {
}
