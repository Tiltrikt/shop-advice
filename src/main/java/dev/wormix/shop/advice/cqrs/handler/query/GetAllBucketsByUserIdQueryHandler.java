package dev.wormix.shop.advice.cqrs.handler.query;

import dev.crashbandicootfm.mediator.model.CommandHandler;
import dev.wormix.shop.advice.cqrs.query.GetAllBucketsByUserIdQuery;
import dev.wormix.shop.advice.model.BucketModel;
import dev.wormix.shop.advice.model.UserModel;
import dev.wormix.shop.advice.service.bucket.BucketService;
import dev.wormix.shop.advice.service.user.UserService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetAllBucketsByUserIdQueryHandler implements CommandHandler<GetAllBucketsByUserIdQuery, List<BucketModel>> {

  @NotNull BucketService bucketService;

  @NotNull UserService userService;

  @Override
  public @NotNull List<BucketModel> handle(@NotNull GetAllBucketsByUserIdQuery command) {
    UserModel user = userService.getById(command.getUserId());
    return bucketService.getAllByUser(user);
  }
}
