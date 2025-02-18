package com.drinkhere.drinklystore.domain.service.freedrinkhistory;

import com.drinkhere.drinklystore.common.annotation.DomainService;
import com.drinkhere.drinklystore.domain.entity.FreeDrinkHistory;
import com.drinkhere.drinklystore.domain.repository.FreeDrinkHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FreeDrinkHistoryQueryService {
    private final FreeDrinkHistoryRepository freeDrinkHistoryRepository;

    public List<FreeDrinkHistory> getFreeDrinkHistories(Long storeId) {
        return freeDrinkHistoryRepository.findAllByStoreId(storeId);
    }
}