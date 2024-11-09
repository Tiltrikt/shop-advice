package dev.wormix.shop.advice.repository;

import dev.wormix.shop.advice.model.ProductItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItemModel, Long> {

}
