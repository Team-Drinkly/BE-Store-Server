package com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.response.GetMemberFreeDrinkHistoryResponse;
import com.drinkhere.drinklystore.domain.entity.FreeDrinkHistory;
import com.drinkhere.drinklystore.domain.service.freedrinkhistory.FreeDrinkHistoryQueryService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationService
@RequiredArgsConstructor
public class GetMemberFreeDrinkHistoriesUseCase {

    private final FreeDrinkHistoryQueryService freeDrinkHistoryQueryService;

    public List<GetMemberFreeDrinkHistoryResponse> getMemberFreeDrinkHistory(Long memberId, Long subscribeId) {
        List<FreeDrinkHistory> freeDrinkHistoriesByMemberId = freeDrinkHistoryQueryService.getMemberFreeDrinkHistories(memberId, subscribeId);

        return freeDrinkHistoriesByMemberId.stream()
                .map(GetMemberFreeDrinkHistoryResponse::toDto)
                .collect(Collectors.toList());
    }

}
