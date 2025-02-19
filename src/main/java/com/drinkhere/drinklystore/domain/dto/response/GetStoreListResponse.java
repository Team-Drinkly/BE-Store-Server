package com.drinkhere.drinklystore.domain.dto.response;

import com.drinkhere.drinklystore.domain.entity.Store;

public record GetStoreListResponse(
        Long storeId,
        String storeName,
        String storeAddress
) {
    public static GetStoreListResponse toDto(Store store) {
        return new GetStoreListResponse(store.getId(), store.getStoreName(), store.getStoreAddress() + store.getStoreDetailAddress());
    }
}
