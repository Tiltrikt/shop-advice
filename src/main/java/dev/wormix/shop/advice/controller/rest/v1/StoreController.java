package dev.wormix.shop.advice.controller.rest.v1;

import dev.crashbandicootfm.mediator.Mediatr;
import dev.wormix.shop.advice.api.StoreBucketPrice;
import dev.wormix.shop.advice.cqrs.query.FindNearbyStoreQuery;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
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
  @SuppressWarnings("unchecked")
  public @NotNull List<StoreBucketPrice> findBestNearbyStore(
      @RequestParam long userId,
      @RequestParam double latitude,
      @RequestParam double longitude,
      @RequestParam long bucketId
  ) {
    FindNearbyStoreQuery query = new FindNearbyStoreQuery(userId, latitude, longitude, bucketId);
    return mediator.dispatch(query, List.class);
  }

//  @GetMapping("/best")
//  public @NotNull StoreBucketPrice getBestStoreRecommendation(@RequestParam long userId) {
//    return mediator.dispatch();
//  }
}
