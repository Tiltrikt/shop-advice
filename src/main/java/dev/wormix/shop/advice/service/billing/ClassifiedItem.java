package dev.wormix.shop.advice.service.billing;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassifiedItem {

  String name;

  String clazz;

  double unitPrice;

  int quantity;

}
