package com.drinkhere.drinklystore.application.presentation.freedrink;

import com.drinkhere.drinklystore.application.presentation.freedrink.docs.FreeDrinkControllerDocs;
import com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory.CreateFreeDrinkHistoryUseCase;
import com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory.GetMemberFreeDrinkHistoriesUseCase;
import com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory.ValidateMemberFreeDrinkUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequest;
import com.drinkhere.drinklystore.domain.dto.response.GetMemberFreeDrinkHistoryResponse;
import com.drinkhere.drinklystore.domain.entity.FreeDrinkHistory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/store/m/free-drink")
@RequiredArgsConstructor
public class FreeDrinkController implements FreeDrinkControllerDocs {

    private final CreateFreeDrinkHistoryUseCase createFreeDrinkHistoryUseCase;
    private final GetMemberFreeDrinkHistoriesUseCase getMemberFreeDrinkHistoriesUseCase;
    private final ValidateMemberFreeDrinkUseCase validateMemberFreeDrinkUseCase;

    @PostMapping
    public ApplicationResponse<String> createFreeDrinkHistory(
            @RequestHeader(value = "member-id", required = false) Long memberId,
            @RequestHeader(value = "subscribe-id", required = false) Long subscribeId,
            @RequestHeader(value = "is-subscribed", required = false) String isSubscribe,
            @RequestBody CreateFreeDrinkHistoryRequest createFreeDrinkHistoryRequest
    ) {
        createFreeDrinkHistoryUseCase.createFreeDrinkHistoryV2(isSubscribe, memberId, subscribeId, createFreeDrinkHistoryRequest);
        return ApplicationResponse.created("Free Drink History를 성공적으로 생성했습니다.");
    }

    @GetMapping("/validate/{storeId}")
    public ApplicationResponse<Boolean> validateFreeDrink(
            @RequestHeader(value = "member-id", required = false) Long memberId,
            @RequestHeader(value = "subscribe-id", required = false) Long subscribeId,
            @RequestHeader(value = "is-subscribed", required = false) String isSubscribe,
            @PathVariable(name = "storeId") Long storeId
    ) {
        return ApplicationResponse.ok(validateMemberFreeDrinkUseCase.validateMemberFreeDrinkUseCase(memberId, storeId), "해당 날짜에 특정 유저가 해당 가게에서 멤버쉽 사용 여부");
    }

    @GetMapping("/client/{memberId}/{subscribeId}")
    public ApplicationResponse<List<GetMemberFreeDrinkHistoryResponse>> getMemberFreeDrinkHistory(
            @PathVariable Long memberId,
            @PathVariable Long subscribeId
    ) {
        return ApplicationResponse.ok(getMemberFreeDrinkHistoriesUseCase.getMemberFreeDrinkHistory(memberId, subscribeId), "특정 멤버에 대한 멤버쉽 사용 정보 조회입니다.");
    }
}
