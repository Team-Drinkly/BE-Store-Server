package com.drinkhere.drinklystore.application.presentation.freedrink;

import com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory.CreateFreeDrinkHistoryUseCase;
import com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory.GetMemberFreeDrinkHistoriesUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequestV2;
import com.drinkhere.drinklystore.domain.dto.response.GetFreeDrinkHistoriesResponseV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/store/m/free-drink-v2")
@RequiredArgsConstructor
public class FreeDrinkControllerV2 {
    private final CreateFreeDrinkHistoryUseCase createFreeDrinkHistoryUseCase;
    private final GetMemberFreeDrinkHistoriesUseCase getMemberFreeDrinkHistoriesUseCase;

    @PostMapping
    public ApplicationResponse<String> createFreeDrinkHistory(
            @RequestHeader(value = "member-id", required = false) Long memberId,
            @RequestHeader(value = "subscribe-id", required = false) Long subscribeId,
            @RequestHeader(value = "is-subscribed", required = false) String isSubscribe,
            @RequestBody CreateFreeDrinkHistoryRequestV2 createFreeDrinkHistoryRequest
    ) {
        createFreeDrinkHistoryUseCase.createFreeDrinkHistoryV2(isSubscribe, memberId, subscribeId, createFreeDrinkHistoryRequest);
        return ApplicationResponse.created("Free Drink History를 성공적으로 생성했습니다.");
    }

    @GetMapping
    public ApplicationResponse<GetFreeDrinkHistoriesResponseV2> getFreeDrinkHistory(
            @RequestHeader("member-id") Long memberId,
            @RequestHeader(value = "subscribe-id", required = false) Long subscribeId
    ) {
        return ApplicationResponse.ok(getMemberFreeDrinkHistoriesUseCase.getFreeDrinkHistoriesV2(memberId, subscribeId), "멤버쉽 사용 이력을 성공적으로 조회했습니다.");
    }

    @GetMapping("/images/{imageId}")
    public ApplicationResponse<String> getFreeDrinkImage(@PathVariable Long imageId) {
        return ApplicationResponse.ok(getMemberFreeDrinkHistoriesUseCase.getFreeDrinkImageUrl(imageId));
    }
}
