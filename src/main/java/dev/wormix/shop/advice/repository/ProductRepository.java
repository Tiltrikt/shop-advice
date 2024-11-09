package dev.wormix.shop.advice.repository;

import dev.wormix.shop.advice.model.ProductModel;
import dev.wormix.shop.advice.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

  @NotNull List<ProductModel> findAllByStore(@NotNull StoreModel store);

}
