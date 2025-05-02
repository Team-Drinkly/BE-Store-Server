package com.drinkhere.drinklystore.application.presentation.store;

import com.drinkhere.drinklystore.application.presentation.store.docs.StoreAdminControllerDocs;
import com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory.GetFreeDrinkHistoriesUseCase;
import com.drinkhere.drinklystore.application.service.Impl.store.GetOwnerMainPageUseCase;
import com.drinkhere.drinklystore.application.service.Impl.store.GetStoreListUseCase;
import com.drinkhere.drinklystore.application.service.Impl.store.RegisterStoreUseCase;
import com.drinkhere.drinklystore.application.service.Impl.store.UpdateStoreUseCase;
import com.drinkhere.drinklystore.application.service.UpdateImagesUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.RegisterStoreRequest;
import com.drinkhere.drinklystore.domain.dto.request.StoreImageUpdateRequest;
import com.drinkhere.drinklystore.domain.dto.request.UpdateStoreRequest;
import com.drinkhere.drinklystore.domain.dto.response.*;
import com.drinkhere.drinklystore.domain.service.store.StoreCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store/o")
public class StoreAdminController implements StoreAdminControllerDocs {

    private final RegisterStoreUseCase registerStoreUseCase;
    private final UpdateStoreUseCase updateStoreUseCase;
    private final UpdateImagesUseCase menuUpdateImagesUseCaseImpl;
    private final UpdateImagesUseCase availableDrinkUpdateImagesUseCaseImpl;

    private final GetStoreListUseCase getStoreListUseCase;
    private final GetOwnerMainPageUseCase getOwnerMainPageUseCase;
    private final GetFreeDrinkHistoriesUseCase getFreeDrinkHistoriesUseCase;

    @PostMapping
    public ApplicationResponse<StoreResponse> registerStore(
            @RequestBody RegisterStoreRequest registerStoreRequest
    ) {
        return ApplicationResponse.created(registerStoreUseCase.registerStore(registerStoreRequest), "업체 등록이 성공적으로 처리됐습니다.");
    }

    @PatchMapping("/{storeId}")
    public ApplicationResponse<GetStoreResponse> updateStore(
            @PathVariable Long storeId,
            @RequestHeader(value = "owner-id", required = false) Long ownerId,
            @RequestBody UpdateStoreRequest updateStoreRequest
    ) {
        return ApplicationResponse.ok(updateStoreUseCase.updateStore(storeId, updateStoreRequest), "업체 업데이트가 성공적으로 처리됐습니다.");
    }


    @PatchMapping("/{storeId}/images")
    public ApplicationResponse<StoreResponse> updateStoreImages(
            @PathVariable Long storeId,
            @RequestHeader(value = "owner-id", required = false) Long ownerId,
            @RequestBody StoreImageUpdateRequest request
    ) {
        StoreResponse storeResponse = null;
        if (request.type().equals("availableDrinks")) storeResponse = availableDrinkUpdateImagesUseCaseImpl.updateImages(storeId, request);
        else if (request.type().equals("menu")) storeResponse = menuUpdateImagesUseCaseImpl.updateImages(storeId, request);
        return ApplicationResponse.ok(storeResponse, "업체 이미지 업데이트를 성공적으로 처리했습니다.");
    }

    @GetMapping("/owner")
    public ApplicationResponse<List<GetStoreListResponse>> getStoreList(
            @RequestHeader(value = "owner-id", required = false) Long ownerId
    ) {
        return ApplicationResponse.ok(getStoreListUseCase.getStoreList(ownerId), "해당 사장님이 등록한 제휴 업체 목록입니다.");
    }

    @GetMapping("/{storeId}")
    public ApplicationResponse<GetOwnerMainPageResponse> getOwnerMainPage(
            @PathVariable Long storeId,
            @RequestHeader(value = "owner-id", required = false) Long ownerId
    ) {
        return ApplicationResponse.ok(getOwnerMainPageUseCase.getOwnerMainPage(storeId), "제휴 업체 명과 최근 멤버쉽 사용 이력을 반환합니다.");
    }

    @GetMapping("/free-drink/{storeId}")
    public ApplicationResponse<List<GetFreeDrinkHistoryResponse>> getFreeDrinkHistories(
            @PathVariable Long storeId,
            @RequestHeader(value = "owner-id", required = false) Long ownerId
    ) {
        return ApplicationResponse.ok(getFreeDrinkHistoriesUseCase.getFreeDrinkHistories(storeId),"Free Drink History를 성공적으로 조회했습니다.");
    }

    @GetMapping("/temp")
    public ApplicationResponse<OwnerIdResponse> getMemberId(
            @RequestHeader("owner-id") Long ownerId
    ) {
        return ApplicationResponse.ok(new OwnerIdResponse(ownerId), "사장님 ID입니다");
    }
}