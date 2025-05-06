package com.drinkhere.drinklystore.domain.service.store;

import com.drinkhere.drinklystore.common.annotation.DomainService;
import com.drinkhere.drinklystore.domain.dto.OpeningHours;
import com.drinkhere.drinklystore.domain.dto.request.ImageInfo;
import com.drinkhere.drinklystore.domain.dto.request.UpdateStoreRequest;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.entity.StoreImage;
import com.drinkhere.drinklystore.domain.enums.StoreImageType;
import com.drinkhere.drinklystore.domain.repository.StoreImageRepository;
import com.drinkhere.drinklystore.domain.repository.StoreRepository;
import com.drinkhere.drinklystore.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@DomainService
@Transactional
@RequiredArgsConstructor
public class StoreCommandService {
    private final StoreRepository storeRepository;
    private final StoreImageRepository storeImageRepository;

    public Store save(final Store store) {
        return storeRepository.save(store);
    }

    public Store updateStore(Store store, UpdateStoreRequest updateStoreRequest) {
        // PATCH: 필드가 null이 아니면 업데이트 안함
        if (updateStoreRequest.storeName() != null) store.setStoreName(updateStoreRequest.storeName());
        if (updateStoreRequest.storeMainImageUrl() != null) store.setStoreMainImageUrl(updateStoreRequest.storeMainImageUrl());
        if (updateStoreRequest.storeDescription() != null) store.setStoreDescription(updateStoreRequest.storeDescription());
        if (updateStoreRequest.openingHours() != null) store.setOpeningHours(JsonUtil.serialization(updateStoreRequest.openingHours()));
        if (updateStoreRequest.storeTel() != null) store.setStoreTel(updateStoreRequest.storeTel());
        if (updateStoreRequest.storeAddress() != null) store.setStoreAddress(updateStoreRequest.storeAddress());
        if (updateStoreRequest.instagramUrl() != null) store.setInstagramUrl(updateStoreRequest.instagramUrl());
        if (updateStoreRequest.availableDays() != null) store.setAvailableDays(updateStoreRequest.availableDays());
        if (updateStoreRequest.isReady() != null) store.setIsReady(updateStoreRequest.isReady());

        // DirtyChecking
        return store;
    }

    // 이미지 추가 메서드
    public void addImages(Store store, StoreImageType storeImageType,List<ImageInfo> newImageUrls) {
        List<StoreImage> newStoreImages = newImageUrls.stream()
                .map(imageInfo -> StoreImage.builder()
                                            .store(store)
                                            .storeImageType(storeImageType)
                                            .storeImageUrl(imageInfo.imageUrl())
                                            .storeImageDescription(imageInfo.description())
                                            .build()
                )
                .collect(Collectors.toList());

        storeImageRepository.saveAll(newStoreImages);
    }

    // 이미지 삭제 메서드
    public void removeImages(List<Long> removeImageIds) {
        List<StoreImage> imagesToDelete = storeImageRepository.findAllById(removeImageIds);
        storeImageRepository.deleteAll(imagesToDelete);
    }
}
