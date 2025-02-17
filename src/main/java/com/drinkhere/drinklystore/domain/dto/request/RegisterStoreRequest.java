package com.drinkhere.drinklystore.domain.dto.request;

import com.drinkhere.drinklystore.clientgeocoding.dto.Coordinates;
import com.drinkhere.drinklystore.domain.entity.Store;

public record RegisterStoreRequest(
        Long ownerId,
        String storeName,
        String storeTel,
        String storeAddress,
        String storeDetailAddress
) {
    public Store toEntity(Coordinates coordinates) {
        return Store.builder()
                .ownerId(ownerId)
                .storeName(storeName)
                .storeTel(storeTel)
                .storeAddress(storeAddress + storeDetailAddress)
                .latitude(coordinates.latitude())
                .longitude(coordinates.longitude())
                .build();
    }
}
