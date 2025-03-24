package com.drinkhere.drinklystore.application.service.Impl.store;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.request.UpdateStoreRequest;
import com.drinkhere.drinklystore.domain.dto.response.StoreResponse;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.service.store.StoreCommandService;
import com.drinkhere.drinklystore.domain.service.store.StoreQueryService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class UpdateStoreUseCase {
    private final StoreQueryService storeQueryService;
    private final StoreCommandService storeCommandService;

    public StoreResponse updateStore(Long storeId, UpdateStoreRequest updateStoreRequest) {
        Store updatedStore = storeCommandService.updateStore(storeQueryService.findById(storeId), updateStoreRequest);
        return StoreResponse.toDto(updatedStore);
    }
}
