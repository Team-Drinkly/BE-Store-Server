package com.drinkhere.drinklystore.application.presentation.freedrink;

import com.drinkhere.drinklystore.application.presentation.freedrink.docs.FreeDrinkControllerDocs;
import com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory.CreateFreeDrinkHistoryUseCase;
import com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory.GetMemberFreeDrinkHistoriesUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequest;
import com.drinkhere.drinklystore.domain.dto.response.GetMemberFreeDrinkHistoryResponse;
import com.drinkhere.drinklystore.domain.entity.FreeDrinkHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/store/m/free-drink")
@RequiredArgsConstructor
public class FreeDrinkController implements FreeDrinkControllerDocs {

    private final CreateFreeDrinkHistoryUseCase createFreeDrinkHistoryUseCase;
    private final GetMemberFreeDrinkHistoriesUseCase getMemberFreeDrinkHistoriesUseCase;

    @PostMapping
    public ApplicationResponse<String> createFreeDrinkHistory(
            @RequestHeader(value = "member-id", required = false) Long memberId,
            @RequestHeader(value = "subscribe-id", required = false) Long subscribeId,
            @RequestHeader(value = "is-subscribed", required = false) String isSubscribe,
            @RequestBody CreateFreeDrinkHistoryRequest createFreeDrinkHistoryRequest
    ) {
        createFreeDrinkHistoryUseCase.createFreeDrinkHistory(isSubscribe, memberId, subscribeId, createFreeDrinkHistoryRequest);
        return ApplicationResponse.created("Free Drink History를 성공적으로 생성했습니다.");
    }

    @GetMapping("/client/{memberId}/{subscribeId}")
    public ApplicationResponse<List<GetMemberFreeDrinkHistoryResponse>> getMemberFreeDrinkHistory(
            @PathVariable Long memberId,
            @PathVariable Long subscribeId
    ) {
        return ApplicationResponse.ok(getMemberFreeDrinkHistoriesUseCase.getMemberFreeDrinkHistory(memberId, subscribeId), "특정 멤버에 대한 멤버쉽 사용 정보 조회입니다.");
    }
}
