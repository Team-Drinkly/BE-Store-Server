package com.drinkhere.drinklystore.application.service.Impl.store;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.response.GetStoresByLocationResponse;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.service.store.StoreQueryService;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationService
@RequiredArgsConstructor
public class GetStoresByLocationUseCase {
    private final StoreQueryService storeQueryService;
    private final PresignedUrlService presignedUrlService;

    public List<GetStoresByLocationResponse> getStoresByLocation(double latitude, double longitude, int radius, String searchKeyword) {
        List<Store> storesByLocation = storeQueryService.getStoresByLocation(latitude, longitude, radius, searchKeyword);

        Map<Boolean, List<GetStoresByLocationResponse>> partitioned =
                storesByLocation.stream()
                        .map(store -> GetStoresByLocationResponse.toDto(store, presignedUrlService))
                        .collect(Collectors.partitioningBy(GetStoresByLocationResponse::isAvailable));

        List<GetStoresByLocationResponse> result = new ArrayList<>();
        result.addAll(partitioned.getOrDefault(true, Collections.emptyList()));   // isAvailable=true 먼저
        result.addAll(partitioned.getOrDefault(false, Collections.emptyList()));  // 그다음 false

        return result;
    }

}
