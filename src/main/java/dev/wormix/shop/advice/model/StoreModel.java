package dev.wormix.shop.advice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@Table(name = "stores")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  @Column(nullable = false)
  @NotNull
  String name;

  @Column(nullable = false)
  @NotNull
  String country;

  @NotNull
  String city;

  @Column(name = "ico", nullable = false)
  @NotNull
  String ico;

  @Column(name = "ic_dph")
  String icDph;

  @Column(nullable = false)
  @NotNull
  String dic;

  @Column(nullable = false)
  @NotNull
  String municipality;

  @Column(name = "postal_code", nullable = false)
  @NotNull
  String postalCode;

  @Column(name = "property_registration_number")
  String propertyRegistrationNumber;

  @Column(name = "street_name", nullable = false)
  @NotNull
  String streetName;

  @Column(name = "building_number")
  String buildingNumber;

  @Column(nullable = false)
  @NotNull
  String category;

  @Column(columnDefinition = "geography(Point, 4326)")
  @NotNull Point location;
}