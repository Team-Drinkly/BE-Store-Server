package com.drinkhere.drinklystore.application.service.Impl;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.request.UpdateStoreRequest;
import com.drinkhere.drinklystore.domain.dto.response.StoreResponse;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.service.StoreCommandService;
import com.drinkhere.drinklystore.domain.service.StoreQueryService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class UpdateStoreUseCase {
    private final StoreQueryService storeQueryService;
    private final StoreCommandService storeCommandService;

    public StoreResponse updateStore(Long storeId, UpdateStoreRequest updateStoreRequest) {
        Store store = storeQueryService.findById(storeId);
        return StoreResponse.toDto(storeCommandService.updateStore(store, updateStoreRequest));
    }
}
