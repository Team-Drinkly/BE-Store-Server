package com.drinkhere.drinklystore.application.service.Impl.store;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.response.GetStoreListResponse;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.service.store.StoreQueryService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationService
@RequiredArgsConstructor
public class GetStoreListUseCase {
    private final StoreQueryService storeQueryService;

    public List<GetStoreListResponse> getStoreList(Long ownerId) {
        List<Store> byOwnerId = storeQueryService.findByOwnerId(ownerId);
        return byOwnerId.stream()
                .map(GetStoreListResponse::toDto)
                .toList();
    }
}
