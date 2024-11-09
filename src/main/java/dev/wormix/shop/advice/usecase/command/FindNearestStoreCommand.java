package dev.wormix.shop.advice.usecase.command;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FindNearestStoreCommand {

  long distanceInMeters;

  double latitude;

  double longitude;
}
