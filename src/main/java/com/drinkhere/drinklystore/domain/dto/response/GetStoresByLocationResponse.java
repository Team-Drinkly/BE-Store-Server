package com.drinkhere.drinklystore.domain.dto.response;

import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.entity.StoreImage;
import com.drinkhere.drinklystore.domain.enums.StoreImageType;

import java.util.List;
import java.util.stream.Collectors;

public record GetStoresByLocationResponse(
        Long id,
        String storeName,
        String storeMainImageUrl,
        String latitude,
        String longitude,
        String storeTel,
        String storeAddress,
        List<String> availableDrinks
) {
    public static GetStoresByLocationResponse toDto(Store store, PresignedUrlService presignedUrlService) {
        String presignedUrl = presignedUrlService.getPresignedUrlForGet(store.getStoreMainImageUrl());
        
        List<String> availableDrinks = store.getStoreImages().stream()
                .filter(image -> image.getStoreImageType() == StoreImageType.AVAILABLE_DRINK)
                .map(StoreImage::getStoreImageDescription)
                .collect(Collectors.toList());

        return new GetStoresByLocationResponse(
                store.getId(),
                store.getStoreName(),
                presignedUrl,
                store.getLatitude(),
                store.getLongitude(),
                store.getStoreTel(),
                store.getStoreAddress(),
                availableDrinks
        );
    }
}
