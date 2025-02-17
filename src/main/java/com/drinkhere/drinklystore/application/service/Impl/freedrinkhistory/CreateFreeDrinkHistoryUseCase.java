package com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequest;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.service.freedrinkhistory.FreeDrinkHistoryCommandService;
import com.drinkhere.drinklystore.domain.service.store.StoreQueryService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class CreateFreeDrinkHistoryUseCase {
    private final FreeDrinkHistoryCommandService freeDrinkHistoryCommandService;
    private final StoreQueryService storeQueryService;

    public void createFreeDrinkHistory(Long memberId, Long subscribeId, CreateFreeDrinkHistoryRequest createFreeDrinkHistoryRequest) {
        Store store = storeQueryService.findById(createFreeDrinkHistoryRequest.storeId());

        // Feign Client
        freeDrinkHistoryCommandService.createFreeDrinkHistory(memberId, subscribeId, store, createFreeDrinkHistoryRequest.providedDrink());
    }

}
