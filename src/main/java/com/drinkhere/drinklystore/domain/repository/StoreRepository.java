package com.drinkhere.drinklystore.domain.repository;

import com.drinkhere.drinklystore.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findById(Long id);

    default Store findByIdOrThrow(Long id) {
        return this.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));
    }
}
