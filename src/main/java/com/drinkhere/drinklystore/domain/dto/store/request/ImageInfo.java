package com.drinkhere.drinklystore.domain.dto.store.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record ImageInfo(

        @Schema(description = "이미지 Presigned URL", example = "https://example.com/image.jpg", required = true)
        String imageUrl, // 이미지 URL

        @Schema(description = "이미지 설명", example = "이미지 설명이 들어갑니다.", required = false)
        String description // 이미지 설명 (선택 사항)
) {
    public static ImageInfo toDto(String imageUrl, String description) {
        return new ImageInfo(imageUrl, description);
    }
}
