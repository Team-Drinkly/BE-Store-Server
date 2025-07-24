package com.drinkhere.drinklystore.application.service.Impl.store;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.store.response.GetStoreResponse;
import com.drinkhere.drinklystore.domain.entity.store.Store;
import com.drinkhere.drinklystore.domain.service.store.StoreQueryService;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import com.drinkhere.drinklystore.redis.util.RedisUtil;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class GetStoreUseCase {
    private final StoreQueryService storeQueryService;
    private final PresignedUrlService presignedUrlService;
    private final RedisUtil redisUtil;

    public GetStoreResponse getStore(Long storeId) {
        Store store = storeQueryService.findByIdWithImages(storeId);

        // 조회수 처리
        String storeName = store.getStoreName();
        String redisField = storeId + ":" + storeName;
        redisUtil.incrementHashValue("store:viewCount", redisField);

        return GetStoreResponse.toDto(store, presignedUrlService);
    }

    public String getStoreName(Long storeId) {
        return storeQueryService.findStoreNameById(storeId);
    }

    public String getStoreNameTestExcepted(Long storeId) {
        return storeQueryService.findStoreNameByIdTestExcepted(storeId);
    }
}
