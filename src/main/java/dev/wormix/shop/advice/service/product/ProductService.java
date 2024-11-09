package dev.wormix.shop.advice.service.product;

import dev.wormix.shop.advice.model.ProductModel;
import dev.wormix.shop.advice.model.StoreModel;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

public interface ProductService {

  @Unmodifiable
  @NotNull List<ProductModel> findAllByStore(@NotNull StoreModel store);
}
