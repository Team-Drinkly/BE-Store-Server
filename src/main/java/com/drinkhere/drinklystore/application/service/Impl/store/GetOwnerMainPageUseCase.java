package com.drinkhere.drinklystore.application.service.Impl.store;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.response.GetFreeDrinkHistoryResponse;
import com.drinkhere.drinklystore.domain.dto.response.GetOwnerMainPageResponse;
import com.drinkhere.drinklystore.domain.entity.FreeDrinkHistory;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.service.freedrinkhistory.FreeDrinkHistoryQueryService;
import com.drinkhere.drinklystore.domain.service.store.StoreQueryService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationService
@RequiredArgsConstructor
public class GetOwnerMainPageUseCase {
    private final StoreQueryService storeQueryService;
    private final FreeDrinkHistoryQueryService freeDrinkHistoryQueryService;

    public GetOwnerMainPageResponse getOwnerMainPage(Long storeId) {
        Store store = storeQueryService.findById(storeId);

        List<FreeDrinkHistory> freeDrinkHistories = freeDrinkHistoryQueryService.getRecentFreeDrinkHistories(storeId);

        List<GetFreeDrinkHistoryResponse> collect = freeDrinkHistories.stream()
                .map(GetFreeDrinkHistoryResponse::toDto)
                .collect(Collectors.toList());

        return GetOwnerMainPageResponse.toDto(store.getStoreName(), collect);
    }

}
