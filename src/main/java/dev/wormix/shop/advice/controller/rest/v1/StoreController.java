package dev.wormix.shop.advice.controller.rest.v1;

import dev.crashbandicootfm.mediator.Mediatr;
import dev.wormix.shop.advice.api.StoreBucketPrice;
import dev.wormix.shop.advice.cqrs.query.FindNearbyStoreQuery;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@CrossOrigin("*")
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

}
