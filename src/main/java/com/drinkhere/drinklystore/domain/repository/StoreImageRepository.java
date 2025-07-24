package com.drinkhere.drinklystore.domain.repository;

import com.drinkhere.drinklystore.common.exception.store.StoreException;
import com.drinkhere.drinklystore.domain.entity.store.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static com.drinkhere.drinklystore.common.exception.store.StoreErrorCode.STORE_IMAGE_NOT_FOUND;

public interface StoreImageRepository extends JpaRepository<StoreImage, Long> {

    Optional<StoreImage> findStoreImageById(Long id);

    default StoreImage findStoreImageByIdOrThrow(Long id) {
        return this.findStoreImageById(id)
                .orElseThrow(() -> new StoreException(STORE_IMAGE_NOT_FOUND));
    }
}
