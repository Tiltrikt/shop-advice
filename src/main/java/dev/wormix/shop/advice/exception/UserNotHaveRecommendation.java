package dev.wormix.shop.advice.exception;

public class UserNotHaveRecommendation extends RuntimeException {

  public UserNotHaveRecommendation(String message) {
    super(message);
  }
}
