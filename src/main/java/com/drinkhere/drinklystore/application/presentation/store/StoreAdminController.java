package com.drinkhere.drinklystore.application.presentation.store;

import com.drinkhere.drinklystore.application.presentation.store.docs.StoreAdminControllerDocs;
import com.drinkhere.drinklystore.application.service.Impl.RegisterStoreUseCase;
import com.drinkhere.drinklystore.application.service.Impl.UpdateStoreUseCase;
import com.drinkhere.drinklystore.application.service.UpdateImagesUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.RegisterStoreRequest;
import com.drinkhere.drinklystore.domain.dto.request.StoreImageUpdateRequest;
import com.drinkhere.drinklystore.domain.dto.request.UpdateStoreRequest;
import com.drinkhere.drinklystore.domain.dto.response.StoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store/o")
public class StoreAdminController implements StoreAdminControllerDocs {

    private final RegisterStoreUseCase registerStoreUseCase;
    private final UpdateStoreUseCase updateStoreUseCase;
    private final UpdateImagesUseCase menuUpdateImagesUseCaseImpl;
    private final UpdateImagesUseCase availableDrinkUpdateImagesUseCaseImpl;

    @PostMapping
    public ApplicationResponse<StoreResponse> registerStore(
            @RequestBody RegisterStoreRequest registerStoreRequest
    ) {
        return ApplicationResponse.created(registerStoreUseCase.registerStore(registerStoreRequest), "업체 등록이 성공적으로 처리됐습니다.");
    }

    @PatchMapping("/{storeId}")
    public ApplicationResponse<StoreResponse> updateStore(
            @PathVariable Long storeId,
            @RequestHeader(value = "owner-id", required = false) Long ownerId,
            @RequestBody UpdateStoreRequest updateStoreRequest
    ) {
        return ApplicationResponse.ok(updateStoreUseCase.updateStore(storeId, updateStoreRequest), "업체 업데이트가 성공적으로 처리됐습니다.");
    }


    @PatchMapping("/{storeId}/images")
    public ApplicationResponse<String> updateStoreImages(
            @PathVariable Long storeId,
            @RequestHeader(value = "owner-id", required = false) Long ownerId,
            @RequestBody StoreImageUpdateRequest request
    ) {
        if (request.type().equals("availableDrinks")) availableDrinkUpdateImagesUseCaseImpl.updateImages(storeId, request);
        else if (request.type().equals("menu")) menuUpdateImagesUseCaseImpl.updateImages(storeId, request);
        return ApplicationResponse.ok( "업체 이미지 업데이트를 성공적으로 처리했습니다.");
    }
}