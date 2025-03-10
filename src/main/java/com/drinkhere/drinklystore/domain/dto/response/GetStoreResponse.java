package com.drinkhere.drinklystore.domain.dto.response;

import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import com.drinkhere.drinklystore.domain.dto.request.ImageInfo;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.enums.StoreImageType;

import java.util.List;
import java.util.stream.Collectors;

public record GetStoreResponse(
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
        String latitude,
        String longitude,
        List<ImageInfo> availableDrinkImageUrls,
        List<ImageInfo> menuImageUrls
) {
    public static GetStoreResponse toDto(Store store, PresignedUrlService presignedUrlService) {
        String presignedUrl = presignedUrlService.getPresignedUrlForGet(store.getStoreMainImageUrl());
        
        List<ImageInfo> availableDrinkImages = store.getStoreImages().stream()
                .filter(image -> image.getStoreImageType() == StoreImageType.AVAILABLE_DRINK)
                .map(image -> ImageInfo.toDto(presignedUrlService.getPresignedUrlForGet(image.getStoreImageUrl()), image.getStoreImageDescription()))
                .collect(Collectors.toList());

        List<ImageInfo> menuImages = store.getStoreImages().stream()
                .filter(image -> image.getStoreImageType() == StoreImageType.MENU)
                .map(image -> ImageInfo.toDto(presignedUrlService.getPresignedUrlForGet(image.getStoreImageUrl()), image.getStoreImageDescription()))
                .collect(Collectors.toList());

        return new GetStoreResponse(
                store.getId(),
                store.getOwnerId(),
                store.getStoreName(),
                presignedUrl,
                store.getStoreDescription(),
                store.getOpeningHours(),
                store.getStoreTel(),
                store.getStoreAddress(),
                store.getInstagramUrl(),
                store.getAvailableDays(),
                store.getLatitude(),
                store.getLongitude(),
                availableDrinkImages,
                menuImages
        );
    }
}
