package dev.wormix.shop.advice.repository;

import dev.wormix.shop.advice.model.BucketModel;
import dev.wormix.shop.advice.model.ReceiptModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<ReceiptModel, Long> {

}
