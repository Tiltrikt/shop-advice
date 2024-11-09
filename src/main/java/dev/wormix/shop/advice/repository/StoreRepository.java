package dev.wormix.shop.advice.repository;

import dev.wormix.shop.advice.model.StoreModel;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends CrudRepository<StoreModel, Long> {

  @Query("SELECT o FROM StoreModel o WHERE " +
      "ST_Distance(o.location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)) < :distance")
  @NotNull List<StoreModel> findByLocationNear(@Param("longitude") double longitude,
                                               @Param("latitude") double latitude,
                                               @Param("distance") double distance);

  @NotNull List<StoreModel> findAllByCity(@NotNull String city);

  @NotNull Optional<StoreModel> findByName(@NotNull String name);

}
