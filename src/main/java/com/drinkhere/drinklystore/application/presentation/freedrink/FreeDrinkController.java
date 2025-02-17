package com.drinkhere.drinklystore.application.presentation.freedrink;

import com.drinkhere.drinklystore.application.presentation.freedrink.docs.FreeDrinkControllerDocs;
import com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory.CreateFreeDrinkHistoryUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/store/m/free-drink")
@RequiredArgsConstructor
public class FreeDrinkController implements FreeDrinkControllerDocs {

    private final CreateFreeDrinkHistoryUseCase createFreeDrinkHistoryUseCase;

    @PostMapping
    public ApplicationResponse<String> createFreeDrinkHistory(
            @RequestHeader(value = "member-id", required = false) Long memberId,
            @RequestHeader(value = "subscribe-id", required = false) Long subscribeId,
            @RequestBody CreateFreeDrinkHistoryRequest createFreeDrinkHistoryRequest
    ) {
        createFreeDrinkHistoryUseCase.createFreeDrinkHistory(memberId, subscribeId, createFreeDrinkHistoryRequest);
        return ApplicationResponse.created("Free Drink History를 성공적으로 생성했습니다.");
    }
}
