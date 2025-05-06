package com.drinkhere.drinklystore.domain.dto.response;

import com.drinkhere.drinklystore.domain.dto.OpeningHours;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.util.JsonUtil;

import java.util.List;

public record StoreResponse(
        Long storeId,
        Long ownerId,
        String storeName,
        String storeMainImageUrl,
        String storeDescription,
        String isOpen,
        String openingInfo,
        List<OpeningHours> openingHours,
        String storeTel,
        String storeAddress,
        String storeDetailAddress,
        String instagramUrl,
        String availableDays,
        String latitude,
        String longitude,
        List<ImageInfoResponse> availableDrinkImageUrls,
        List<ImageInfoResponse> menuImageUrls
) {

    public static StoreResponse toDto(Store store, String storeMainImageUrl, List<ImageInfoResponse> availableDrinkImageUrls, List<ImageInfoResponse> menuImageUrls) {
        List<OpeningHours> openingHours;
        if (store.getOpeningHours() == null) openingHours = null;
        else openingHours = JsonUtil.deserialization(store.getOpeningHours());

        return new StoreResponse(
                store.getId(),
                store.getOwnerId(),
                store.getStoreName(),
                storeMainImageUrl,
                store.getStoreDescription(),
                null,
                null,
                openingHours,
                store.getStoreTel(),
                store.getStoreAddress(),
                store.getStoreDetailAddress(),
                store.getInstagramUrl(),
                store.getAvailableDays(),
                store.getLatitude(),
                store.getLongitude(),
                availableDrinkImageUrls,
                menuImageUrls
        );
    }
}
