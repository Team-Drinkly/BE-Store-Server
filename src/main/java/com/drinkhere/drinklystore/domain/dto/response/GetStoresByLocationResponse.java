package com.drinkhere.drinklystore.domain.dto.response;

import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.entity.StoreImage;
import com.drinkhere.drinklystore.domain.enums.StoreImageType;

import java.util.List;
import java.util.stream.Collectors;

public record GetStoresByLocationResponse(
        Long id,
        String storeName,
        String latitude,
        String longitude,
        String storeTel,
        String storeAddress,
        List<String> availableDrinks
) {
    public static GetStoresByLocationResponse toDto(Store store) {
        List<String> availableDrinks = store.getStoreImages().stream()
                .filter(image -> image.getStoreImageType() == StoreImageType.AVAILABLE_DRINK)
                .map(StoreImage::getStoreImageDescription)
                .collect(Collectors.toList());

        return new GetStoresByLocationResponse(
                store.getId(),
                store.getStoreName(),
                store.getLatitude(),
                store.getLongitude(),
                store.getStoreTel(),
                store.getStoreAddress(),
                availableDrinks
        );
    }
}
