package com.drinkhere.drinklystore.domain.dto.response;

import com.drinkhere.drinklystore.domain.dto.request.ImageInfo;
import io.swagger.v3.oas.annotations.media.Schema;

public record ImageInfoResponse(
        Long imageId,
        String imageUrl, // 이미지 URL
        String description // 이미지 설명 (선택 사항)
) {
    public static ImageInfoResponse toDto(Long imageId, String imageUrl, String description) {
        return new ImageInfoResponse(imageId, imageUrl, description);
    }
}

