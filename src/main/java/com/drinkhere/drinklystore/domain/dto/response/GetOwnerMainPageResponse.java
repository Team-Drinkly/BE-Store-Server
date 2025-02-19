package com.drinkhere.drinklystore.domain.dto.response;

import java.util.List;

public record GetOwnerMainPageResponse(
        String storeName,
        List<GetFreeDrinkHistoryResponse> getFreeDrinkHistoryResponseList
) {
    public static GetOwnerMainPageResponse toDto(String storeName, List<GetFreeDrinkHistoryResponse> getFreeDrinkHistoryResponseList) {
        return new GetOwnerMainPageResponse(storeName, getFreeDrinkHistoryResponseList);
    }
}
