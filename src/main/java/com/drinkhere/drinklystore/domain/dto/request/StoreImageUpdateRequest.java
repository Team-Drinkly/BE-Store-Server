package com.drinkhere.drinklystore.domain.dto.request;

import java.util.List;

public record StoreImageUpdateRequest(
        String type, // 이미지 타입 ("availableDrinks" 또는 "menuImages")
        List<ImageInfo> newImageUrls, // 추가할 이미지 리스트
        List<Long> removeImageIds // 삭제할 이미지들의 ID 리스트
) {

}