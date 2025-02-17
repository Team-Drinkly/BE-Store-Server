package com.drinkhere.drinklystore.domain.dto.request;

public record GetAllStoresMapViewRequest(
        String latitude,
        String longitude,
        String radius
) {
}
