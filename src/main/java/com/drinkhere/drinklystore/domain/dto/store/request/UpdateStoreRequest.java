package com.drinkhere.drinklystore.domain.dto.store.request;

import com.drinkhere.drinklystore.domain.dto.OpeningHours;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "제휴 업체 정보 업데이트")
public record UpdateStoreRequest(

        @Schema(description = "제휴 업체 이름", example = "여기, 꼬치네", required = true)
        @Size(min = 1, max = 30, message = "업체 이름은 1자 이상 30자 이하로 입력해야 합니다.")
        String storeName,

        @Schema(description = "제휴 업체 메인 이미지 URL", example = "https://example.com/main_image.jpg", required = false)
        String storeMainImageUrl,

        @Schema(description = "제휴 업체 설명", example = "맛있는 꼬치구이 전문점", required = false)
        String storeDescription,

        @Schema(description = "제휴 업체 운영 시간", example = "Notion을 참고해주세요.(JSON 직렬화하여 String으로 저장)", required = false)
        List<OpeningHours> openingHours,

        @Schema(description = "제휴 업체 전화번호", example = "02-1234-5678", required = true)
        @Size(max = 30, message = "전화번호는 30자 이하로 입력해야 합니다.")
        String storeTel,

        @Schema(description = "제휴 업체 주소 (도로명 주소 또는 지번 주소)", example = "서울특별시 노원구 동일로192길 62", required = true)
        @Size(max = 100, message = "주소는 100자 이하로 입력해야 합니다.")
        String storeAddress,

        @Schema(description = "업체 상세 주소", example = "2층", required = true)
        @Size(max = 50, message = "상세 주소는 50자 이하로 입력해야 합니다.")
        String storeDetailAddress,

        @Schema(description = "제휴 업체 인스타그램 URL", example = "https://instagram.com/yourstore", required = false)
        @Size(max = 100, message = "인스타그램(SNS) 주소는 100자 이하로 입력해야 합니다.")
        String instagramUrl,

        @Schema(description = "이용 가능한 가능한 요일", example = "[\"월\", \"화\", \"수\", \"목\", \"금\"]", required = false)
        @Size(max = 50, message = "이용 가능한 가능한 요일은 50자 이하로 입력해야 합니다.")
        String availableDays,

        Boolean isReady
) {
}
