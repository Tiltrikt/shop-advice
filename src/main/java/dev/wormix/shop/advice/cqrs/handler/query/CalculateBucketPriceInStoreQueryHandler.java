package dev.wormix.shop.advice.cqrs.handler.query;

import dev.crashbandicootfm.mediator.model.CommandHandler;
import dev.wormix.shop.advice.cqrs.query.CalculateBucketPriceInStoreQuery;
import dev.wormix.shop.advice.exception.ProductNotFoundException;
import dev.wormix.shop.advice.model.ProductModel;
import dev.wormix.shop.advice.service.product.ProductService;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CalculateBucketPriceInStoreQueryHandler implements CommandHandler<CalculateBucketPriceInStoreQuery, Double> {

  @NotNull ProductService productService;

  @Override
  public @NotNull Double handle(@NotNull CalculateBucketPriceInStoreQuery command) {
    List<ProductModel> productModelList = productService.findAllByStore(command.getStoreModel());
    double totalPrice = 0.0;

    for (String productName : command.getBucketModel().getProductList()) {
      Optional<Double> productPrice = productModelList.stream()
          .filter(productModel -> productModel.getName().equals(productName))
          .map(ProductModel::getPrice)
          .findFirst();

      if (productPrice.isEmpty()) {
        throw new ProductNotFoundException("Product with name '%s' not found".formatted(productName));
      }

      totalPrice += productPrice.get();
    }

    return totalPrice;
  }
}
