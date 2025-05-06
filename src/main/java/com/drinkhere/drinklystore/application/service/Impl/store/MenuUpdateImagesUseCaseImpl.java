package com.drinkhere.drinklystore.application.service.Impl.store;

import com.drinkhere.drinklystore.application.service.UpdateImagesUseCase;
import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.request.StoreImageUpdateRequest;
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
public class MenuUpdateImagesUseCaseImpl implements UpdateImagesUseCase {
    private final StoreQueryService storeQueryService;
    private final StoreCommandService storeCommandService;
    private final PresignedUrlService presignedUrlService;

    @Override
    public StoreResponse updateImages(Long storeId, StoreImageUpdateRequest storeImageUpdateRequest) {
        // 1. Store 엔티티 조회
        Store store = storeQueryService.findById(storeId);

        // 2. 새로 추가할 이미지 처리 (newImageUrls)
        if (storeImageUpdateRequest.newImageUrls() != null && !storeImageUpdateRequest.newImageUrls().isEmpty()) {
            storeCommandService.addImages(store, StoreImageType.MENU, storeImageUpdateRequest.newImageUrls());
        }

        // 3. 삭제할 이미지 처리 (removeImageIds)
        if (storeImageUpdateRequest.removeImageIds() != null && !storeImageUpdateRequest.removeImageIds().isEmpty()) {
            storeCommandService.removeImages(storeImageUpdateRequest.removeImageIds());
        }

        // 4. Store 엔티티 업데이트 후 다시 조회
        Store updatedStore = storeQueryService.findByIdWithImages(storeId);

        // 5. 이미지 타입별로 분리
        List<ImageInfoResponse> availableDrinkImageUrls = updatedStore.getStoreImages().stream()
                .filter(image -> image.getStoreImageType() == StoreImageType.AVAILABLE_DRINK)
                .map(image -> new ImageInfoResponse(image.getId(), presignedUrlService.getPresignedUrlForGet(image.getStoreImageUrl()), image.getStoreImageDescription()))
                .toList();

        List<ImageInfoResponse> menuImageUrls = updatedStore.getStoreImages().stream()
                .filter(image -> image.getStoreImageType() == StoreImageType.MENU)
                .map(image -> new ImageInfoResponse(image.getId(), presignedUrlService.getPresignedUrlForGet(image.getStoreImageUrl()), image.getStoreImageDescription()))
                .toList();

        String presignedUrl = presignedUrlService.getPresignedUrlForGet(updatedStore.getStoreMainImageUrl());

        // 6. StoreResponse DTO 반환
        return StoreResponse.toDto(updatedStore, presignedUrl, availableDrinkImageUrls, menuImageUrls);
    }
}