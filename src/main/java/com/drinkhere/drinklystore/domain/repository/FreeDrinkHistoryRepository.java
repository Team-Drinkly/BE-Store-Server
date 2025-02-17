package com.drinkhere.drinklystore.domain.repository;

import com.drinkhere.drinklystore.domain.entity.FreeDrinkHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeDrinkHistoryRepository extends JpaRepository<FreeDrinkHistory, Long> {

    List<FreeDrinkHistory> findAllByStoreId(Long storeId);

}
