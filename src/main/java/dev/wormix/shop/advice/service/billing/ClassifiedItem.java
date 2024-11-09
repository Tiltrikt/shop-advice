package dev.wormix.shop.advice.service.billing;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassifiedItem {

  String name;

  String clazz;

  double unitPrice;

  double quantity;

}
