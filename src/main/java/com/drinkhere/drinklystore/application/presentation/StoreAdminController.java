package com.drinkhere.drinklystore.application.presentation;

import com.drinkhere.drinklystore.application.presentation.docs.StoreAdminConrollerDocs;
import com.drinkhere.drinklystore.application.service.RegisterStoreUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.RegisterStoreRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
public class StoreAdminController implements StoreAdminConrollerDocs {
    private final RegisterStoreUseCase registerStoreUseCase;
    @PostMapping("/temp")
    public ApplicationResponse<String> registerStore(
            @RequestBody RegisterStoreRequest registerStoreRequest
    ) {
        registerStoreUseCase.registerStore(registerStoreRequest);
        return ApplicationResponse.ok("업체 등록이 성공적으로 처리됐습니다.");
    }

    @PatchMapping
    public ApplicationResponse<String> updateStore() {

        return ApplicationResponse.ok("업체 업데이트가 성공적으로 처리됐습니다.");
    }
}
