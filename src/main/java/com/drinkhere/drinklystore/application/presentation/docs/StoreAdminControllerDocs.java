package com.drinkhere.drinklystore.application.presentation.docs;


import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.RegisterStoreRequest;
import com.drinkhere.drinklystore.domain.dto.request.StoreImageUpdateRequest;
import com.drinkhere.drinklystore.domain.dto.request.UpdateStoreRequest;
import com.drinkhere.drinklystore.domain.dto.response.StoreResponse;

public interface StoreAdminControllerDocs {

    ApplicationResponse<StoreResponse> registerStore(RegisterStoreRequest request);
    ApplicationResponse<StoreResponse> updateStore(Long storeId, Long ownerId, UpdateStoreRequest request);
    ApplicationResponse<String> updateStoreImages(Long storeId, Long ownerId, StoreImageUpdateRequest request);
}
