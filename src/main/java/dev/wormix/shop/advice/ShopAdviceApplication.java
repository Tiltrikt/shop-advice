package dev.wormix.shop.advice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "dev.wormix.shop.advice"
})
public class ShopAdviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShopAdviceApplication.class, args);
  }
}