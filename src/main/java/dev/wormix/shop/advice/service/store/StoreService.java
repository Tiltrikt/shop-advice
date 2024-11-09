package dev.wormix.shop.advice.service.store;

import dev.wormix.shop.advice.model.StoreModel;
import org.jetbrains.annotations.NotNull;

public interface StoreService {

  @NotNull StoreModel getById(long id);
}
