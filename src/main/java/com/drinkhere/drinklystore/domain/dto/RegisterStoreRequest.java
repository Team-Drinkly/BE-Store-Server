package com.drinkhere.drinklystore.domain.dto;

import com.drinkhere.drinklystore.clientgeocoding.dto.Coordinates;
import com.drinkhere.drinklystore.domain.entity.Store;

public record RegisterStoreRequest(
        Long ownerId,
        String storeName,
        String storeTel,
        String storeAddress
) {
    public Store toEntity(Coordinates coordinates) {
        return Store.builder()
                .ownerId(ownerId)
                .storeName(storeName)
                .storeTel(storeTel)
                .storeAddress(storeAddress)
                .latitude(coordinates.latitude())
                .longitude(coordinates.longitude())
                .build();
    }
}
