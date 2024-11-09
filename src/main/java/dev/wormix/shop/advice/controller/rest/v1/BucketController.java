//package dev.wormix.shop.advice.controller.rest.v1;
//
//
//import dev.wormix.shop.advice.dto.Bucket;
//import dev.wormix.shop.advice.usecase.AddBucketUseCase;
//import dev.wormix.shop.advice.usecase.command.AddBucketCommand;
//import java.util.List;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/buckets")
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class BucketController {
//
//  @NotNull AddBucketUseCase addBucket;
//
//  @NotNull GetAllBucketsUseCase getAllBuckets;
//
//  @PostMapping
//  public void addBucket(@NotNull @RequestBody Bucket bucket) {
//    AddBucketCommand addBucketCommand = new AddBucketCommand(bucket.getName(), bucket.getProductList());
//    addBucket.execute(addBucketCommand);
//  }
//
//  @GetMapping("/{userId}")
//  public List<Bucket> getAll(@PathVariable String userId) {
//
//  }
//}
