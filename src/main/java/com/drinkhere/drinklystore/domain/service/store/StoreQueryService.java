package com.drinkhere.drinklystore.domain.service.store;

import com.drinkhere.drinklystore.common.annotation.DomainService;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.entity.StoreImage;
import com.drinkhere.drinklystore.domain.repository.StoreImageRepository;
import com.drinkhere.drinklystore.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreQueryService {
    private final StoreRepository storeRepository;
    private final StoreImageRepository storeImageRepository;

    public Store findById(Long id) {
        return storeRepository.findByIdOrThrow(id);
    }

    public String findStoreNameById(Long storeId) {return storeRepository.findStoreNameById(storeId);}

    public String findStoreNameByIdTestExcepted(Long storeId) {return storeRepository.findStoreNameByIdTestExcepted(storeId);}

    public List<Store> findByOwnerId(Long ownerId) {
        return storeRepository.findByOwnerId(ownerId);
    }

    public List<Store> getStoresByLocation(Double latitude, Double longitude, int radius, String searchKeyWord) {
        return storeRepository.findStoresByLocation(latitude, longitude, radius, searchKeyWord);
    }

    public Store findByIdWithImages(Long storeId) {
        return storeRepository.findByIdWithImagesOrThrow(storeId);
    }

    public StoreImage findStoreImageByIdOrThrow(Long imageId) { return storeImageRepository.findStoreImageByIdOrThrow(imageId); }
}
