package com.drinkhere.drinklystore.application.service;

import com.drinkhere.drinklystore.domain.dto.store.request.StoreImageUpdateRequest;
import com.drinkhere.drinklystore.domain.dto.store.response.StoreResponse;

public interface UpdateImagesUseCase {
    StoreResponse updateImages(Long storeId, StoreImageUpdateRequest storeImageUpdateRequest);
}
