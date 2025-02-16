package com.drinkhere.drinklystore.domain.service;

import com.drinkhere.drinklystore.common.annotation.DomainService;
import com.drinkhere.drinklystore.domain.dto.response.GetStoresByLocationResponse;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreQueryService {
    private final StoreRepository storeRepository;

    public Store findById(Long id) {
        return storeRepository.findByIdOrThrow(id);
    }


    public List<GetStoresByLocationResponse> getStoresByLocation(Double latitude, Double longitude, int radius, String searchKeyWord) {
        List<Store> stores = storeRepository.findStoresByLocation(latitude, longitude, radius, searchKeyWord);
        return stores.stream()
                .map(GetStoresByLocationResponse::toDto)
                .toList();
    }

    public Store findByIdWithImages(Long storeId) {
        return storeRepository.findByIdWithImagesOrThrow(storeId);
    }
}
