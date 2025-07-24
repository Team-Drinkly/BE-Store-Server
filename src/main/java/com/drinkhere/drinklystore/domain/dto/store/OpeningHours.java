package com.drinkhere.drinklystore.domain.dto.store;

public record OpeningHours(
        String day,
        boolean isOpen,
        String openTime,
        String closeTime
) {
}
