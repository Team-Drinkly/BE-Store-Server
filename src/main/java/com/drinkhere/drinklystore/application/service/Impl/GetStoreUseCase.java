package com.drinkhere.drinklystore.application.service.Impl;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.response.GetStoreResponse;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.service.StoreQueryService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class GetStoreUseCase {
    private final StoreQueryService storeQueryService;

    public GetStoreResponse getStore(Long storeId) {
        Store store = storeQueryService.findByIdWithImages(storeId);
        return GetStoreResponse.toDto(store);
    }
}
