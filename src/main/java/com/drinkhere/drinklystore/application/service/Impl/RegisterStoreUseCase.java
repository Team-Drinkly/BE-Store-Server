package com.drinkhere.drinklystore.application.service.Impl;

import com.drinkhere.drinklystore.clientgeocoding.dto.Coordinates;
import com.drinkhere.drinklystore.clientgeocoding.service.GeocodingUseCase;
import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.request.RegisterStoreRequest;
import com.drinkhere.drinklystore.domain.dto.response.StoreResponse;
import com.drinkhere.drinklystore.domain.service.StoreCommandService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class RegisterStoreUseCase {
    private final StoreCommandService storeCommandService;
    private final GeocodingUseCase geocodingUseCase;

    public StoreResponse registerStore(RegisterStoreRequest request) {
        Coordinates coordinates = geocodingUseCase.getCoordinates(request.storeAddress());
        return StoreResponse.toDto(storeCommandService.save(request.toEntity(coordinates)));
    }

}
