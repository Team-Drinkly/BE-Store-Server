package com.drinkhere.drinklystore.application.service;

import com.drinkhere.drinklystore.domain.dto.request.StoreImageUpdateRequest;
import com.drinkhere.drinklystore.domain.dto.response.StoreResponse;

public interface UpdateImagesUseCase {
    StoreResponse updateImages(Long storeId, StoreImageUpdateRequest storeImageUpdateRequest);
}
