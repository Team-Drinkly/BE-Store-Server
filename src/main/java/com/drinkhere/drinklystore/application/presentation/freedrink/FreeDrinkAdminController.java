package com.drinkhere.drinklystore.application.presentation.freedrink;

import com.drinkhere.drinklystore.application.presentation.docs.FreeDrinkAdminControllerDocs;
import com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory.GetFreeDrinkHistoriesUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequest;
import com.drinkhere.drinklystore.domain.dto.response.GetFreeDrinkHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/store/o/free-drink")
@RequiredArgsConstructor
public class FreeDrinkAdminController implements FreeDrinkAdminControllerDocs {

    private final GetFreeDrinkHistoriesUseCase getFreeDrinkHistoriesUseCase;

    @GetMapping("/{storeId}")
    public ApplicationResponse<List<GetFreeDrinkHistoryResponse>> getFreeDrinkHistories(
            @PathVariable Long storeId,
            @RequestHeader(value = "owner-id", required = false) Long ownerId
    ) {
        return ApplicationResponse.ok(getFreeDrinkHistoriesUseCase.getFreeDrinkHistories(storeId),"Free Drink History를 성공적으로 조회했습니다.");
    }
}
