package com.drinkhere.drinklystore.domain.dto.response;

import java.util.List;

public record GetFreeDrinkHistoriesResponseV2(
        int usedCount,
        List<DrinkHistory> drinksHistory
) {


    public record DrinkHistory(
            Long freeDrinkHistoryId,
            String storeName,
            Long providedDrinkImageId,
            String providedDrink,
            String usageDate
    ) {}
}
