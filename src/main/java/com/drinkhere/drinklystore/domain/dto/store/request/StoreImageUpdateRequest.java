package com.drinkhere.drinklystore.domain.dto.store.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "제휴 업체 이미지 업데이트 요청 객체")
public record StoreImageUpdateRequest(

        @Schema(description = "이미지 타입(이용 가능한 주류 이미지를 업데이트 한다면 availableDrinks, 메뉴 이미지를 업데이트한다면 menuImages", example = "availableDrinks", required = true)
        String type, // 이미지 타입 ("availableDrinks" 또는 "menuImages")

        @Schema(description = "추가할 이미지들의 URL 리스트", required = true)
        List<ImageInfo> newImageUrls, // 추가할 이미지 리스트

        @Schema(description = "삭제할 이미지들의 ID 리스트", example = "[1,2]", required = true)
        List<Long> removeImageIds // 삭제할 이미지들의 ID 리스트

) {
}
