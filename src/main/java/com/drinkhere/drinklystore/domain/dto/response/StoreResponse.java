package com.drinkhere.drinklystore.domain.dto.response;

import com.drinkhere.drinklystore.domain.dto.OpeningHours;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public record StoreResponse(
        Long storeId,
        Long ownerId,
        String storeName,
        String storeMainImageUrl,
        String storeDescription,
        List<OpeningHours> openingHours,
        String storeTel,
        String storeAddress,
        String storeDetailAddress,
        String instagramUrl,
        String availableDays,
        String latitude,
        String longitude,
        String businessRegistrationNumber
) {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static StoreResponse toDto(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getOwnerId(),
                store.getStoreName(),
                store.getStoreMainImageUrl(),
                store.getStoreDescription(),
                JsonUtil.deserialization(store.getOpeningHours()),
                store.getStoreTel(),
                store.getStoreAddress(),
                store.getStoreDetailAddress(),
                store.getInstagramUrl(),
                store.getAvailableDays(),
                store.getLatitude(),
                store.getLongitude(),
                store.getBusinessRegistrationNumber()
        );
    }


}
