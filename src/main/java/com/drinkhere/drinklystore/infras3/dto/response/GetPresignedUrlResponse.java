package com.drinkhere.drinklystore.infras3.dto.response;

public record GetPresignedUrlResponse(
        String url,
        String filePath
) {
    public static GetPresignedUrlResponse of(String url, String filePath) {
        return new GetPresignedUrlResponse(url,filePath);
    }
}
