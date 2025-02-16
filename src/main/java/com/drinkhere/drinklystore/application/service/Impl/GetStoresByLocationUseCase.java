package com.drinkhere.drinklystore.application.service.Impl;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.response.GetStoresByLocationResponse;
import com.drinkhere.drinklystore.domain.service.StoreQueryService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationService
@RequiredArgsConstructor
public class GetStoresByLocationUseCase {
    private final StoreQueryService storeQueryService;
    public List<GetStoresByLocationResponse> getStoresByLocation(double latitude, double longitude, int radius, String searchKeyword) {
        return storeQueryService.getStoresByLocation(latitude, longitude, radius, searchKeyword);
    }
}
