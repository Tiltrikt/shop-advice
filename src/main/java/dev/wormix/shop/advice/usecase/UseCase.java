package dev.wormix.shop.advice.usecase;

import org.jetbrains.annotations.NotNull;

public interface UseCase<K, T> {

  @NotNull T execute(@NotNull K command);
}
