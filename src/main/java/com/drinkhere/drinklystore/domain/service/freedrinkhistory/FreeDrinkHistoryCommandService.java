package com.drinkhere.drinklystore.domain.service.freedrinkhistory;

import com.drinkhere.drinklystore.common.annotation.DomainService;
import com.drinkhere.drinklystore.domain.entity.FreeDrinkHistory;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.repository.FreeDrinkHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class FreeDrinkHistoryCommandService {
    private final FreeDrinkHistoryRepository freeDrinkHistoryRepository;

    public void createFreeDrinkHistory(Long memberId, Long subscribeId, Store store, String providedDrink) {
        FreeDrinkHistory freeDrinkHistory = FreeDrinkHistory.builder()
                .memberId(memberId)
                .subscribeId(subscribeId)
                .store(store)
                .providedDrink(providedDrink)
                .build();
        freeDrinkHistoryRepository.save(freeDrinkHistory);
    }
}
