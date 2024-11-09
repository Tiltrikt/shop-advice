package dev.wormix.shop.advice.controller.rest.v1;

import dev.crashbandicootfm.mediator.Mediatr;
import dev.wormix.shop.advice.api.StoreBucketPrice;
import dev.wormix.shop.advice.cqrs.query.FindNearbyStoreQuery;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StoreController {

  @NotNull Mediatr mediator;

  @GetMapping()
  public @NotNull StoreBucketPrice findBestNearbyStore(
      @RequestParam long userId,
      @RequestParam double latitude,
      @RequestParam double longitude,
      @RequestParam double bucketId
  ) {
    FindNearbyStoreQuery query = new FindNearbyStoreQuery(userId, latitude, longitude, bucketId);
    return mediator.dispatch(query, StoreBucketPrice.class);
  }

  @GetMapping("/best/{userId}")
  public @NotNull StoreRecommendation getBestStoreRecommendation(@PathVariable long userId) {

  }
}
