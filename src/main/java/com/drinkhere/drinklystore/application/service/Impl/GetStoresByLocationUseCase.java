package com.drinkhere.drinklystore.application.service.Impl;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.response.GetStoresByLocationResponse;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.service.StoreQueryService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationService
@RequiredArgsConstructor
public class GetStoresByLocationUseCase {
    private final StoreQueryService storeQueryService;
    private final PresignedUrlService presignedUrlService;

    public List<GetStoresByLocationResponse> getStoresByLocation(double latitude, double longitude, int radius, String searchKeyword) {
        List<Store> storesByLocation = storeQueryService.getStoresByLocation(latitude, longitude, radius, searchKeyword);
        return storesByLocation.stream()
                .map(store -> GetStoresByLocationResponse.toDto(store, presignedUrlService))
                .toList();
    }
}
