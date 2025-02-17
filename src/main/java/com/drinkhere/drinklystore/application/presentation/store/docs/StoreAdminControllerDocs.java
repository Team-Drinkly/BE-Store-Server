package com.drinkhere.drinklystore.application.presentation.store.docs;


import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.RegisterStoreRequest;
import com.drinkhere.drinklystore.domain.dto.request.StoreImageUpdateRequest;
import com.drinkhere.drinklystore.domain.dto.request.UpdateStoreRequest;
import com.drinkhere.drinklystore.domain.dto.response.StoreResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "문의 관련 API", description = "제휴 업체 정보 API 명세입니다.")
public interface StoreAdminControllerDocs {

    ApplicationResponse<StoreResponse> registerStore(RegisterStoreRequest request);
    ApplicationResponse<StoreResponse> updateStore(Long storeId, Long ownerId, UpdateStoreRequest request);
    ApplicationResponse<String> updateStoreImages(Long storeId, Long ownerId, StoreImageUpdateRequest request);
}
