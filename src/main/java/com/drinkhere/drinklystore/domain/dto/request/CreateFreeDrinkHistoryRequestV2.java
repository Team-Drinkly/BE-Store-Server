package com.drinkhere.drinklystore.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "드링클리 멤버쉽 사용 이력 기록 생성 요청")
public record CreateFreeDrinkHistoryRequestV2(

        @Schema(description = "제휴 업체의 고유 ID", example = "1", required = true)
        @NotNull(message = "제휴 업체 ID는 필수입니다.")
        Long storeId,

        @Schema(description = "제공된 주류 이미지 ID", example = "1", required = true)
        @NotNull(message = "제공된 주류 이미지 ID는 필수입니다.")
        Long providedDrinkImageId,

        @Schema(description = "제공된 주류 종류", example = "소주", required = true)
        @NotBlank(message = "제공된 주류 종류는 필수입니다.")
        String providedDrink
) {
}
