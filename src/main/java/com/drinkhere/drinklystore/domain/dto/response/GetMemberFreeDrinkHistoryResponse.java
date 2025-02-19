package com.drinkhere.drinklystore.domain.dto.response;

import com.drinkhere.drinklystore.common.utils.TimeUtil;
import com.drinkhere.drinklystore.domain.entity.FreeDrinkHistory;

public record GetMemberFreeDrinkHistoryResponse(
    Long freeDrinkHistoryId,
    String storeName,
    String providedDrink,
    String usageDate
) {
    public static GetMemberFreeDrinkHistoryResponse toDto(FreeDrinkHistory freeDrinkHistory) {
        return new GetMemberFreeDrinkHistoryResponse(
                freeDrinkHistory.getId(),
                freeDrinkHistory.getStore().getStoreName(),
                freeDrinkHistory.getProvidedDrink(),
                TimeUtil.refineDateTime(freeDrinkHistory.getCreatedDate())
        );
    }
}
