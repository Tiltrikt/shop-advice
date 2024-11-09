package dev.wormix.shop.advice.service.billing;

import dev.wormix.shop.advice.model.ProductItemModel;
import dev.wormix.shop.advice.model.ProductModel;
import dev.wormix.shop.advice.model.ReceiptModel;
import dev.wormix.shop.advice.repository.ProductItemRepository;
import dev.wormix.shop.advice.repository.ProductRepository;
import dev.wormix.shop.advice.repository.ReceiptRepository;
import dev.wormix.shop.advice.repository.StoreRepository;
import dev.wormix.shop.advice.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillStoreService {

  public static final int USER_ID = 1;

  @NotNull UserService userService;

  @NotNull ReceiptRepository receiptRepository;

  @NotNull StoreRepository storeRepository;

  @NotNull ProductRepository productRepository;

  @NotNull ProductItemRepository productItemRepository;

  public void store(Bill bill) {
    var customer = userService.getById(USER_ID);
    var store = storeRepository.findByName(bill.getOrganizationName());
    if (store.isEmpty()) {
      log.warn("Organization [" + bill.getOrganizationName() + "]" + " not found");
      return;
    }

    var receiptModel = ReceiptModel.builder()
      .customer(customer)
      .totalPrice(bill.getTotalPrice())
      .organization(store.get())
      .build();

    receiptModel = receiptRepository.save(receiptModel);
    final ReceiptModel finalReceiptModel = receiptModel;
    bill.getItems().forEach(i -> {
      var product = ProductModel.builder()
          .itemType("K")
          .name(i.getName())
          .price(i.getUnitPrice())
          .category(i.getClazz())
          .store(store.get())
          .build();

      productRepository.save(product);

      var prodItem = ProductItemModel.builder()
        .product(product)
        .receipt(finalReceiptModel)
        .quantity(i.getQuantity())
        .build();

      productItemRepository.save(prodItem);
    });
//
    log.warn("End storing");
  }

}
