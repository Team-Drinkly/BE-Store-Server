package com.drinkhere.drinklystore.application.service;

import com.drinkhere.drinklystore.domain.dto.request.StoreImageUpdateRequest;

public interface UpdateImagesUseCase {
    void updateImages(Long storeId, StoreImageUpdateRequest storeImageUpdateRequest);
}
