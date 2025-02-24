package com.drinkhere.drinklystore.application.presentation.store;

import com.drinkhere.drinklystore.application.presentation.store.docs.StoreControllerDocs;
import com.drinkhere.drinklystore.application.service.Impl.store.GetStoreUseCase;
import com.drinkhere.drinklystore.application.service.Impl.store.GetStoresByLocationUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.response.GetStoresByLocationResponse;
import com.drinkhere.drinklystore.domain.dto.response.GetStoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store/m")
public class StoreController implements StoreControllerDocs {
    private final GetStoresByLocationUseCase getStoresByLocationUseCase;
    private final GetStoreUseCase getStoreUseCase;


    @GetMapping("/list")
    public ApplicationResponse<List<GetStoresByLocationResponse>> getStoresByLocation(
            @RequestParam(defaultValue = "37.63022195215973") double latitude,
            @RequestParam(defaultValue = "127.07671771357782") double longitude,
            @RequestParam(defaultValue = "50") int radius,
            @RequestParam(required = false) String searchKeyword
    ) {
        List<GetStoresByLocationResponse> stores = getStoresByLocationUseCase.getStoresByLocation(latitude, longitude, radius, searchKeyword);
        return ApplicationResponse.ok(stores, "제휴 업체 리스트 반환했습니다.");
    }

    @GetMapping("/list/{storeId}")
    public ApplicationResponse<GetStoreResponse> getStore(@PathVariable Long storeId) {
        return ApplicationResponse.ok(getStoreUseCase.getStore(storeId), "제휴 업체 상세 정보입니다.");
    }


}
