package dev.wormix.shop.advice.exception;

public class BucketNotFoundException extends RuntimeException {

  public BucketNotFoundException(String message) {
    super(message);
  }
}
