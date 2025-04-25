package com.drinkhere.drinklystore.domain.repository;

import com.drinkhere.drinklystore.common.exception.store.StoreException;
import com.drinkhere.drinklystore.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import static com.drinkhere.drinklystore.common.exception.store.StoreErrorCode.STORE_NOT_FOUND;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findById(Long id);

    default Store findByIdOrThrow(Long id) {
        return this.findById(id)
                .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));
    }

    List<Store> findByOwnerId(Long ownerId);

    @Query(value = """
            SELECT s.*, 
                   6371 * acos(
                       cos(radians(:latitude)) * cos(radians(CAST(s.latitude AS DOUBLE))) 
                       * cos(radians(CAST(s.longitude AS DOUBLE)) - radians(:longitude)) 
                       + sin(radians(:latitude)) * sin(radians(CAST(s.latitude AS DOUBLE)))
                   ) AS distance
            FROM store s
            WHERE (
                6371 * acos(
                    cos(radians(:latitude)) * cos(radians(CAST(s.latitude AS DOUBLE))) 
                    * cos(radians(CAST(s.longitude AS DOUBLE)) - radians(:longitude)) 
                    + sin(radians(:latitude)) * sin(radians(CAST(s.latitude AS DOUBLE)))
                )
            ) < :radius
            AND s.is_ready = true
            AND (:searchKeyword IS NULL OR s.store_name LIKE %:searchKeyword%)
            ORDER BY distance ASC
    """, nativeQuery = true)
    List<Store> findStoresByLocation(@Param("latitude") double latitude,
                                     @Param("longitude") double longitude,
                                     @Param("radius") double radius,
                                     @Param("searchKeyword") String searchKeyword);



    @Query("SELECT s FROM Store s LEFT JOIN FETCH s.storeImages WHERE s.id = :storeId")
    Optional<Store> findByIdWithImages(@Param("storeId") Long storeId);

    default Store findByIdWithImagesOrThrow(Long storeId) {
        return this.findByIdWithImages(storeId)
                .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));
    }
}
