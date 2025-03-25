package com.drinkhere.drinklystore.application.service.Impl.store;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.request.UpdateStoreRequest;
import com.drinkhere.drinklystore.domain.dto.response.ImageInfoResponse;
import com.drinkhere.drinklystore.domain.dto.response.StoreResponse;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.enums.StoreImageType;
import com.drinkhere.drinklystore.domain.service.store.StoreCommandService;
import com.drinkhere.drinklystore.domain.service.store.StoreQueryService;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationService
@RequiredArgsConstructor
public class UpdateStoreUseCase {
    private final StoreQueryService storeQueryService;
    private final StoreCommandService storeCommandService;
    private final PresignedUrlService presignedUrlService;

    public StoreResponse updateStore(Long storeId, UpdateStoreRequest updateStoreRequest) {
        Store updatedStore = storeCommandService.updateStore(storeQueryService.findByIdWithImages(storeId), updateStoreRequest);

        // 5. 이미지 타입별로 분리
        List<ImageInfoResponse> availableDrinkImageUrls = updatedStore.getStoreImages().stream()
                .filter(image -> image.getStoreImageType() == StoreImageType.AVAILABLE_DRINK)
                .map(image -> new ImageInfoResponse(image.getId(), image.getStoreImageUrl(), image.getStoreImageDescription()))
                .toList();

        List<ImageInfoResponse> menuImageUrls = updatedStore.getStoreImages().stream()
                .filter(image -> image.getStoreImageType() == StoreImageType.MENU)
                .map(image -> new ImageInfoResponse(image.getId(), image.getStoreImageUrl(), image.getStoreImageDescription()))
                .toList();

        String presignedUrl = presignedUrlService.getPresignedUrlForGet(updatedStore.getStoreMainImageUrl());

        return StoreResponse.toDto(updatedStore, presignedUrl, availableDrinkImageUrls, menuImageUrls);
    }
}
