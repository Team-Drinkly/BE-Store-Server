package com.drinkhere.drinklystore.domain.repository;

import com.drinkhere.drinklystore.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
