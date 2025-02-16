package com.drinkhere.drinklystore.domain.dto.request;

public record UpdateStoreRequest(
        String storeName,
        String storeMainImageUrl,
        String storeDescription,
        String openingHours,
        String storeTel,
        String storeAddress,
        String instagramUrl,
        String availableDays,
        String availableDrinks
) {
}