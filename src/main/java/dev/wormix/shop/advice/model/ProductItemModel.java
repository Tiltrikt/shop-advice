package dev.wormix.shop.advice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_items")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class ProductItemModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false)
  int quantity;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  ProductModel product;

  @ManyToOne
  @JoinColumn(name = "receipt_id", nullable = false)
  ReceiptModel receipt;

  @Column(name = "discount_id")
  long discountId;
}
