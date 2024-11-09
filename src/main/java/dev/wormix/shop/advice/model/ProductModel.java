package dev.wormix.shop.advice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class ProductModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false)
  @NotNull String name;

  @Column(name = "item_type", length = 1, nullable = true)
  String itemType;

  @Column(nullable = false)
  double price;

  @NotNull String category;

  @ManyToOne
  @JoinColumn(name = "organization_id", nullable = false)
  OrganizationModel organization;
}
