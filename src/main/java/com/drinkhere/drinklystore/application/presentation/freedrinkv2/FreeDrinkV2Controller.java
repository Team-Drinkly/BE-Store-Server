package com.drinkhere.drinklystore.application.presentation.freedrinkv2;

import com.drinkhere.drinklystore.application.presentation.freedrink.docs.FreeDrinkControllerDocs;
import com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory.CreateFreeDrinkHistoryUseCase;
import com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory.GetMemberFreeDrinkHistoriesUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequest;
import com.drinkhere.drinklystore.domain.dto.response.GetMemberFreeDrinkHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/store/m/free-drink")
@RequiredArgsConstructor
public class FreeDrinkV2Controller {

    private final CreateFreeDrinkHistoryUseCase createFreeDrinkHistoryUseCase;

    @PostMapping
    public ApplicationResponse<String> createFreeDrinkHistory(
            @RequestHeader(value = "member-id", required = false) Long memberId,
            @RequestHeader(value = "subscribe-id", required = false) Long subscribeId,
            @RequestHeader(value = "is-subscribed", required = false) String isSubscribe,
            @RequestBody CreateFreeDrinkHistoryRequest createFreeDrinkHistoryRequest
    ) {
        createFreeDrinkHistoryUseCase.createFreeDrinkHistoryV2(isSubscribe, memberId, subscribeId, createFreeDrinkHistoryRequest);
        return ApplicationResponse.created("V2 : Free Drink History를 성공적으로 생성했습니다.");
    }
}

