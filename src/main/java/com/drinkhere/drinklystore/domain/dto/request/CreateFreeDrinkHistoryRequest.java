package com.drinkhere.drinklystore.domain.dto.request;

public record CreateFreeDrinkHistoryRequest(
        Long storeId,
        String providedDrink
) {
}
