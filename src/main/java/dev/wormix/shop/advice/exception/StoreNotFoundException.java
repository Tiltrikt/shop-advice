package dev.wormix.shop.advice.exception;

public class StoreNotFoundException extends RuntimeException {

  public StoreNotFoundException(String message) {
    super(message);
  }
}
