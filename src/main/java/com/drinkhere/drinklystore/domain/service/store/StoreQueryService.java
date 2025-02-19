package com.drinkhere.drinklystore.domain.service.store;

import com.drinkhere.drinklystore.common.annotation.DomainService;
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

    public List<Store> findByOwnerId(Long ownerId) {
        return storeRepository.findByOwnerIdOrThrow(ownerId);
    }

    public List<Store> getStoresByLocation(Double latitude, Double longitude, int radius, String searchKeyWord) {
        return storeRepository.findStoresByLocation(latitude, longitude, radius, searchKeyWord);
    }

    public Store findByIdWithImages(Long storeId) {
        return storeRepository.findByIdWithImagesOrThrow(storeId);
    }
}
