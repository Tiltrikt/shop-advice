package dev.wormix.shop.advice.controller.rest.v1;


import dev.crashbandicootfm.mediator.Mediatr;
import dev.wormix.shop.advice.api.Bucket;
import dev.wormix.shop.advice.cqrs.command.CreateBucketCommand;
import dev.wormix.shop.advice.cqrs.query.GetAllBucketsByUserIdQuery;
import dev.wormix.shop.advice.model.BucketModel;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buckets")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BucketController {

  @NotNull Mediatr mediatr;

  @PostMapping
  public void addBucket(@NotNull @RequestBody Bucket bucket) {
    CreateBucketCommand command = new CreateBucketCommand(
        bucket.getUserId(),
        bucket.getName(),
        bucket.getProductList()
    );
    mediatr.dispatch(command, BucketModel.class);
  }

  @GetMapping
  @SuppressWarnings("unchecked")
  public @NotNull List<Bucket> getAll(@RequestParam long userId) {
    GetAllBucketsByUserIdQuery query = new GetAllBucketsByUserIdQuery(userId);
    List<BucketModel> bucketModelList = mediatr.dispatch(query, List.class);
    List<Bucket> buckets = new ArrayList<>();
    for (BucketModel bucketModel : bucketModelList) {
      Bucket bucket = new Bucket(
          bucketModel.getId(),
          bucketModel.getName(),
          bucketModel.getProductList()
      );
      buckets.add(bucket);
    }
    return buckets;
  }
}
