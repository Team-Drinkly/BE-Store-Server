package com.drinkhere.drinklystore.clientgeocoding.dto;

import com.drinkhere.drinklystore.clientgeocoding.webclient.dto.GeocodingResponse;

public record Coordinates(
        String latitude, // 위도
        String longitude // 경도
) {
    public static Coordinates from(GeocodingResponse geocodingResponse) {
        GeocodingResponse.Address address = geocodingResponse.addresses().get(0);
        return new Coordinates(
                address.y(),
                address.x()
        );
    }
}
