package dev.wormix.shop.advice.service.billing;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bill {

  double totalPrice;

  String organizationName;

  List<ClassifiedItem> items;

}
