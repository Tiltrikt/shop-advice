package dev.wormix.shop.advice.service.billing;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Builder
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bill {

  double totalPrice;

  String organizationName;

  List<ClassifiedItem> items;

}
