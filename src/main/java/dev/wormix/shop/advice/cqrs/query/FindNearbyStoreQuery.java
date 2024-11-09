package dev.wormix.shop.advice.cqrs.query;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FindNearbyStoreQuery {

  long userId;

  double latitude;

  double longitude;

  long bucketId;
}
