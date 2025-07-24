package com.drinkhere.drinklystore.domain.dto.request;

import com.drinkhere.drinklystore.clientgeocoding.dto.Coordinates;
import com.drinkhere.drinklystore.domain.entity.store.Store;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "제휴 업체 등록 요청 정보(첫 등록)")
public record RegisterStoreRequest(

        @Schema(description = "제휴 업체 사장님 ID", example = "1", required = true)
        @NotNull(message = "제휴 업체 사장님 ID는 필수입니다.")
        Long ownerId,

        @Schema(description = "제휴 업체 이름", example = "여기, 꼬치네", required = true)
        @NotBlank(message = "제휴 업체 이름은 필수입니다.")
        @Size(min = 1, max = 30, message = "업체 이름은 2자 이상 20자 이하로 입력해야 합니다.")
        String storeName,

        @Schema(description = "제휴 업체 전화번호", example = "02-1234-5678", required = true)
        @NotBlank(message = "제휴 업체 전화번호는 필수입니다.")
        @Size(max = 30, message = "전화번호는 30자 이하로 입력해야 합니다.")
        String storeTel,

        @Schema(description = "제휴 업체 주소 (도로명 주소 또는 지번 주소)", example = "서울특별시 노원구 동일로192길 62", required = true)
        @NotBlank(message = "제휴 업체 주소는 필수입니다.")
        @Size(max = 100, message = "주소는 100자 이하로 입력해야 합니다.")
        String storeAddress,

        @Schema(description = "업체 상세 주소", example = "2층", required = true)
        @Size(max = 50, message = "상세 주소는 50자 이하로 입력해야 합니다.")
        String storeDetailAddress,

        @Schema(description = "사업자 등록번호", example = "123-45-67890", required = true)
        @NotBlank(message = "사업자 등록번호는 필수입니다.")
        @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}$", message = "유효한 사업자 등록번호 형식이 아닙니다. (예: 123-45-67890)")
        String businessRegistrationNumber
) {

    public Store toEntity(Coordinates coordinates) {
        return Store.builder()
                .ownerId(ownerId)
                .storeName(storeName)
                .storeTel(storeTel)
                .storeAddress(storeAddress)
                .storeDetailAddress(storeDetailAddress)
                .businessRegistrationNumber(businessRegistrationNumber)
                .latitude(coordinates.latitude())
                .longitude(coordinates.longitude())
                .build();
    }
}
