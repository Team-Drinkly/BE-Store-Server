package com.drinkhere.drinklystore.domain.repository;

import com.drinkhere.drinklystore.domain.dto.response.GetFreeDrinkHistoriesResponse;
import com.drinkhere.drinklystore.domain.entity.FreeDrinkHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FreeDrinkHistoryRepository extends JpaRepository<FreeDrinkHistory, Long> {

    List<FreeDrinkHistory> findTop3ByStoreIdOrderByCreatedDateDesc(Long storeId);

    List<FreeDrinkHistory> findAllByStoreIdOrderByCreatedDateDesc(Long storeId);

    @Query("SELECT f FROM FreeDrinkHistory f JOIN FETCH f.store WHERE f.memberId = :memberId AND f.subscribeId = :subscribeId ORDER BY f.createdDate DESC")
    List<FreeDrinkHistory> findAllByMemberIdAndSubscribeIdOrderByCreatedDateDesc(@Param("memberId") Long memberId, @Param("subscribeId") Long subscribeId);

    @Query("SELECT f FROM FreeDrinkHistory f JOIN FETCH f.store WHERE f.memberId = :memberId AND f.createdDate > :afterDate ORDER BY f.createdDate DESC")
    List<FreeDrinkHistory> findHistoriesWithStore(@Param("memberId") Long memberId, @Param("afterDate") LocalDateTime afterDate);

    @Query("SELECT f FROM FreeDrinkHistory f " +
            "JOIN FETCH f.store " +
            "JOIN FETCH f.store.storeImages " +
            "WHERE f.memberId = :memberId AND f.createdDate > :afterDate " +
            "ORDER BY f.createdDate DESC"
    )
    List<FreeDrinkHistory> findHistoriesWithStoreV2(@Param("memberId") Long memberId, @Param("afterDate") LocalDateTime afterDate);
}
