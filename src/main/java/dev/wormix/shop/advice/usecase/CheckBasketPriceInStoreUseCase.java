package dev.wormix.shop.advice.usecase;

import dev.wormix.shop.advice.usecase.command.CheckBasketPriceInStoreCommand;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckBasketPriceInStoreUseCase implements UseCase<CheckBasketPriceInStoreCommand, Double> {

  @Override
  public @NotNull Double execute(@NotNull CheckBasketPriceInStoreCommand command) {
    return 0.1;
  }
}
