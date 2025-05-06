package com.drinkhere.drinklystore.domain.dto.response;

import java.util.List;

public record GetFreeDrinkHistoriesResponse(
        int usedCount,
        List<DrinkHistory> drinksHistory
) {


    public record DrinkHistory(
            Long freeDrinkHistoryId,
            String storeName,
            String providedDrink,
            String usageDate
    ) {}
}
