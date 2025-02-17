package com.drinkhere.drinklystore.domain.dto.request;

import com.drinkhere.drinklystore.infras3.aop.Interface.TransformToPresignedUrl;

@TransformToPresignedUrl
public record ImageInfo(
        String imageUrl,
        String description
) {
    public static ImageInfo toDto(String imageUrl, String description) {
        return new ImageInfo(imageUrl, description);
    }
}
