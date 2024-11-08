//package dev.wormix.shop.advice.model;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EntityListeners;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import lombok.experimental.FieldDefaults;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//@Entity
//@Getter
//@Setter
//@Builder
//@ToString
//@AllArgsConstructor
//@Table(name = "discounts")
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@EntityListeners(AuditingEntityListener.class)
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class DiscountModel {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  long id;
//
//  @Column(nullable = false)
//  double quantity;
//
//  @Column(name = "product_id", nullable = false)
//  long productId;
//
//  @Column(name = "fs_receipt_id", nullable = false)
//  long fsReceiptId;
//
//  @Column(name = "discount_id")
//  long discountId;
//}