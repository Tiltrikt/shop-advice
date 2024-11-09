package dev.wormix.shop.advice.service.store;

import dev.wormix.shop.advice.exception.StoreNotFoundException;
import dev.wormix.shop.advice.model.StoreModel;
import dev.wormix.shop.advice.repository.StoreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StoreServiceImpl implements StoreService {

  @NotNull StoreRepository repository;

  @Override
  public @NotNull StoreModel getById(long id) {
    return repository.findById(id)
        .orElseThrow(() -> new StoreNotFoundException("Store with id '%d' not found".formatted(id)));
  }
}
