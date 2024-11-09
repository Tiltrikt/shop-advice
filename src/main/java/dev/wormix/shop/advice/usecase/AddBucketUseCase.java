package dev.wormix.shop.advice.usecase;

import dev.wormix.shop.advice.model.BucketModel;
import dev.wormix.shop.advice.repository.BucketRepository;
import dev.wormix.shop.advice.usecase.command.AddBucketCommand;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddBucketUseCase implements UseCase<AddBucketCommand, BucketModel> {

  @NotNull BucketRepository bucketRepository;

  @Override
  public @NotNull BucketModel execute(@NotNull AddBucketCommand command) {
    BucketModel bucketModel = BucketModel.builder()
        .name(command.getBucketName())
        .productList(command.getProductList())
        .build();
    return bucketRepository.save(bucketModel);
  }
}
