package dev.wormix.shop.advice.repository;

import dev.wormix.shop.advice.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

}
