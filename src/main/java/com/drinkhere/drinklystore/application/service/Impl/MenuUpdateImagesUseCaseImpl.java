package com.drinkhere.drinklystore.application.service.Impl;

import com.drinkhere.drinklystore.application.service.UpdateImagesUseCase;
import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.request.StoreImageUpdateRequest;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.enums.StoreImageType;
import com.drinkhere.drinklystore.domain.service.StoreCommandService;
import com.drinkhere.drinklystore.domain.service.StoreQueryService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class MenuUpdateImagesUseCaseImpl implements UpdateImagesUseCase {
    private final StoreQueryService storeQueryService;
    private final StoreCommandService storeCommandService;

    @Override
    public void updateImages(Long storeId, StoreImageUpdateRequest storeImageUpdateRequest) {
        Store store = storeQueryService.findById(storeId);
        System.out.println("here is menu use case");
        // 1. 새로 추가할 이미지 처리 (newImageUrls)
        if (storeImageUpdateRequest.newImageUrls() != null && !storeImageUpdateRequest.newImageUrls().isEmpty()) {
            storeCommandService.addImages(store, StoreImageType.MENU, storeImageUpdateRequest.newImageUrls());
        }

        // 2. 삭제할 이미지 처리 (removeImageIds)
        if (storeImageUpdateRequest.removeImageIds() != null && !storeImageUpdateRequest.removeImageIds().isEmpty()) {
            storeCommandService.removeImages(storeImageUpdateRequest.removeImageIds());
        }
    }
}