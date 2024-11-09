package dev.wormix.shop.advice.exception;

import org.jetbrains.annotations.NotNull;

public class StoreNotFoundException extends RuntimeException {

  public StoreNotFoundException(String message) {
    super(message);
  }
}
