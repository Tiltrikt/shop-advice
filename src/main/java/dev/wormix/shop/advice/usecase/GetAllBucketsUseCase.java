package dev.wormix.shop.advice.usecase;

import dev.wormix.shop.advice.model.BucketModel;
import dev.wormix.shop.advice.repository.BucketRepository;
import dev.wormix.shop.advice.usecase.command.GetAllBucketsCommand;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetAllBucketsUseCase implements UseCase<GetAllBucketsCommand, List<BucketModel>> {

  @NotNull BucketRepository bucketRepository;

  @Override
  public @NotNull List<BucketModel> execute(@NotNull GetAllBucketsCommand command) {
    return new ArrayList<>();
//    return bucketRepository.findAllById(command.getUserId());
  }
}
