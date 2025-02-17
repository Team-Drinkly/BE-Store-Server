package com.drinkhere.drinklystore.application.service.Impl;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.response.GetStoreResponse;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.service.store.StoreQueryService;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class GetStoreUseCase {
    private final StoreQueryService storeQueryService;
    private final PresignedUrlService presignedUrlService;

    public GetStoreResponse getStore(Long storeId) {
        Store store = storeQueryService.findByIdWithImages(storeId);
        return GetStoreResponse.toDto(store, presignedUrlService);
    }
}
