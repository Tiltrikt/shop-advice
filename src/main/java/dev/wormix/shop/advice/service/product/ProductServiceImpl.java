package dev.wormix.shop.advice.service.product;

import dev.wormix.shop.advice.model.ProductModel;
import dev.wormix.shop.advice.model.StoreModel;
import dev.wormix.shop.advice.repository.ProductRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

  @NotNull ProductRepository repository;

  @Override
  @Unmodifiable
  public @NotNull List<ProductModel> findAllByStore(@NotNull StoreModel store) {
    return List.copyOf(repository.findAllByStore(store));
  }
}
