package com.drinkhere.drinklystore.infras3.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record GetPresignedUrlListRequest(
        @NotEmpty(message = "파일 리스트는 비어 있을 수 없습니다.")
        List<GetPresignedUrlRequest> requests
) {
}
