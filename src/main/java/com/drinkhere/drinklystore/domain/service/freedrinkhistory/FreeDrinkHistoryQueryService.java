package com.drinkhere.drinklystore.domain.service.freedrinkhistory;

import com.drinkhere.drinklystore.common.annotation.DomainService;
import com.drinkhere.drinklystore.domain.dto.response.GetFreeDrinkHistoriesResponse;
import com.drinkhere.drinklystore.domain.dto.response.GetFreeDrinkHistoriesResponseV2;
import com.drinkhere.drinklystore.domain.entity.FreeDrinkHistory;
import com.drinkhere.drinklystore.domain.repository.FreeDrinkHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FreeDrinkHistoryQueryService {

    private final FreeDrinkHistoryRepository freeDrinkHistoryRepository;

    public List<FreeDrinkHistory> getRecentFreeDrinkHistories(Long storeId) {
        return freeDrinkHistoryRepository.findTop3ByStoreIdOrderByCreatedDateDesc(storeId);
    }

    public List<FreeDrinkHistory> getFreeDrinkHistories(Long storeId) {
        return freeDrinkHistoryRepository.findAllByStoreIdOrderByCreatedDateDesc(storeId);
    }

    public List<FreeDrinkHistory> getMemberFreeDrinkHistories(Long memberId, Long subscribeId) {
        return freeDrinkHistoryRepository.findAllByMemberIdAndSubscribeIdOrderByCreatedDateDesc(memberId, subscribeId);
    }

    public List<FreeDrinkHistory> getFreeDrinkHistoriesAtMemberApp(Long memberId) {
        LocalDateTime threeMonthsAgo = LocalDateTime.now().minusMonths(3);
        return freeDrinkHistoryRepository.findHistoriesWithStore(memberId, threeMonthsAgo);
    }
}