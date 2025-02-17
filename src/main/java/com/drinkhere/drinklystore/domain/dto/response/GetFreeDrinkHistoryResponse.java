package com.drinkhere.drinklystore.domain.dto.response;

import com.drinkhere.drinklystore.domain.entity.FreeDrinkHistory;
import com.drinkhere.drinklystore.util.TimeUtil;

public record GetFreeDrinkHistoryResponse(
        Long freeDrinkHistoryId,
        String providedDrink,
        Long memberId,
        String createdAt
) {
    public static GetFreeDrinkHistoryResponse toDto(FreeDrinkHistory freeDrinkHistory) {
        return new GetFreeDrinkHistoryResponse(
                freeDrinkHistory.getId(),
                freeDrinkHistory.getProvidedDrink(),
                freeDrinkHistory.getMemberId(),
                TimeUtil.refineToDateTime(freeDrinkHistory.getCreatedDate())
        );
    }
}
