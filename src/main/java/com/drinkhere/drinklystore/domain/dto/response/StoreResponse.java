package com.drinkhere.drinklystore.domain.dto.response;

import com.drinkhere.drinklystore.domain.entity.Store;

public record StoreResponse(
        Long storeId,
        Long ownerId,
        String storeName,
        String storeMainImageUrl,
        String storeDescription,
        String openingHours,
        String storeTel,
        String storeAddress,
        String instagramUrl,
        String availableDays,
        String availableDrinks,
        String latitude,
        String longitude
) {
    public static StoreResponse toDto(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getOwnerId(),
                store.getStoreName(),
                store.getStoreMainImageUrl(),
                store.getStoreDescription(),
                store.getOpeningHours(),
                store.getStoreTel(),
                store.getStoreAddress(),
                store.getInstagramUrl(),
                store.getAvailableDays(),
                store.getAvailableDrinks(),
                store.getLatitude(),
                store.getLongitude()
        );
    }
}
