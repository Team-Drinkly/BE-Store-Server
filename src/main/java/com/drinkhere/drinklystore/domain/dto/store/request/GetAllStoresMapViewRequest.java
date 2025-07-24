package com.drinkhere.drinklystore.domain.dto.store.request;

public record GetAllStoresMapViewRequest(
        String latitude,
        String longitude,
        String radius
) {
}
