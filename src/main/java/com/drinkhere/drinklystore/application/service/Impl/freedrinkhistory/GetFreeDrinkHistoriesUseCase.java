package com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.freedrink.response.GetFreeDrinkHistoryResponse;
import com.drinkhere.drinklystore.domain.entity.store.FreeDrinkHistory;
import com.drinkhere.drinklystore.domain.service.freedrinkhistory.FreeDrinkHistoryQueryService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationService
@RequiredArgsConstructor
public class GetFreeDrinkHistoriesUseCase {
    private final FreeDrinkHistoryQueryService freeDrinkHistoryQueryService;

    public List<GetFreeDrinkHistoryResponse> getFreeDrinkHistories(Long storeId) {
        List<FreeDrinkHistory> freeDrinkHistories = freeDrinkHistoryQueryService.getFreeDrinkHistories(storeId);

        return freeDrinkHistories.stream()
                .map(GetFreeDrinkHistoryResponse::toDto)
                .collect(Collectors.toList());
    }
}
