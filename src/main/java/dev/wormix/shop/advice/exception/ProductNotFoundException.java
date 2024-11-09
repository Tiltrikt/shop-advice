package dev.wormix.shop.advice.exception;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(String message) {
    super(message);
  }
}
