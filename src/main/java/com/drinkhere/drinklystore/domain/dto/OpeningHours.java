package com.drinkhere.drinklystore.domain.dto;

public record OpeningHours(
        String day,
        boolean isOpen,
        String openTime,
        String closeTime
) {
}
