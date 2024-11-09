package dev.wormix.shop.advice.usecase;

import dev.wormix.shop.advice.model.StoreModel;
import dev.wormix.shop.advice.repository.StoreRepository;
import dev.wormix.shop.advice.usecase.command.FindNearestStoreCommand;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FindNearestStoresUseCase implements UseCase<FindNearestStoreCommand, List<StoreModel>> {

  @NotNull StoreRepository storeRepository;

  @Override
  @Unmodifiable
  public @NotNull List<StoreModel> execute(@NotNull FindNearestStoreCommand command) {
    return List.copyOf(storeRepository.findByLocationNear(command.getLongitude(), command.getLatitude(), command.getDistanceInMeters()));
  }
}
