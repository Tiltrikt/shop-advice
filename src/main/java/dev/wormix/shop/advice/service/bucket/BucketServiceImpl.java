package dev.wormix.shop.advice.service.bucket;

import dev.wormix.shop.advice.exception.BucketNotFoundException;
import dev.wormix.shop.advice.model.BucketModel;
import dev.wormix.shop.advice.model.UserModel;
import dev.wormix.shop.advice.repository.BucketRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BucketServiceImpl implements BucketService {

  @NotNull BucketRepository bucketRepository;

  @Override
  public @NotNull BucketModel getById(long id) {
    return bucketRepository.findById(id)
        .orElseThrow(() -> new BucketNotFoundException("Bucket with id '%d' not found".formatted(id)));
  }

  @Override
  @Unmodifiable
  public @NotNull List<BucketModel> getAllByUser(@NotNull UserModel userModel) {
    return List.copyOf(bucketRepository.findAllByUser(userModel));
  }

  @Override
  public @NotNull BucketModel save(@NotNull BucketModel bucket) {
    return bucketRepository.save(bucket);
  }
}
