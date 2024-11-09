package dev.wormix.shop.advice.runner;

import dev.wormix.shop.advice.repository.StoreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationRunner implements CommandLineRunner {

  @NotNull StoreRepository repository;

  @Override
  public void run(String... args) throws Exception {
      System.out.println(repository.findByLocationNear(48.148, 17.107, 1000));
//    GeometryFactory geometryFactory = new GeometryFactory();
//
//    // Создаем новое расположение с использованием долготы и широты
//    double longitude = 12.34; // задайте ваше значение
//    double latitude = 56.78;  // задайте ваше значение
//
//    // Создаем объект Coordinate
//    Coordinate coordinate = new Coordinate(longitude, latitude);
//
//    // Создаем Point с использованием фабрики
//    Point point = geometryFactory.createPoint(coordinate);
//    OrganizationModel organizationModel = OrganizationModel.builder()
//        .location(point)
//        .build();
//    repository.save(organizationModel);
  }
}
