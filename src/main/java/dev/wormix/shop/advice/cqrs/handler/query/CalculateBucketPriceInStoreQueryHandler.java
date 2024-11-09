package dev.wormix.shop.advice.cqrs.handler.query;

import dev.crashbandicootfm.mediator.model.CommandHandler;
import dev.wormix.shop.advice.cqrs.query.CalculateBucketPriceInStoreQuery;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CalculateBucketPriceInStoreQueryHandler  implements CommandHandler<CalculateBucketPriceInStoreQuery, Double> {

  @Override
  public @NotNull Double handle(@NotNull CalculateBucketPriceInStoreQuery command) {
    return 0.1;
  }
}
