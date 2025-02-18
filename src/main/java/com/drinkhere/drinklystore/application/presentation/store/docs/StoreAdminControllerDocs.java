package com.drinkhere.drinklystore.application.presentation.store.docs;


import com.drinkhere.drinklystore.common.exception.ErrorResponse;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.RegisterStoreRequest;
import com.drinkhere.drinklystore.domain.dto.request.StoreImageUpdateRequest;
import com.drinkhere.drinklystore.domain.dto.request.UpdateStoreRequest;
import com.drinkhere.drinklystore.domain.dto.response.StoreResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "1. 제휴 업체 CREATE, UPDATE 관련 API", description = "제휴 업체 정보 CREATE, UPDATE 관련 API 명세입니다.")
public interface StoreAdminControllerDocs {


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST -  잘못된 요청", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - 서버 오류", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class)))
    })
    @Operation(summary = "제휴 업체 등록 API", description = "제휴 업체를 등록합니다. 나머지 값은 수정 API를 통해 채워넣습니다.")
    ApplicationResponse<StoreResponse> registerStore(
            @Valid @RequestBody RegisterStoreRequest request
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST -  잘못된 요청", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found - 유효하지 않은 storeId", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - 서버 오류", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class)))
    })
    @Operation(summary = "제휴 업체 업데이트 API", description = "제휴 업체 정보를 업데이트합니다.이용가능한 주류와 메뉴 정보(이미지 및 설명)는 별개의 API로 빠져있습니다.")
    ApplicationResponse<StoreResponse> updateStore(
            @Valid @PathVariable Long storeId,
            @Valid @RequestHeader(value = "owner-id", required = false) Long ownerId,
            @Valid @RequestBody UpdateStoreRequest request
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - 유효성 검사 실패", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - 서버 오류", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class)))
    })
    @Operation(
            summary = "제휴 업체 이미지 업데이트 API",
            description = "제휴 업체의 이미지를 업데이트합니다. 사용자는 이미지 타입을 지정하고, 새로운 이미지를 추가하거나 기존 이미지를 삭제할 수 있습니다. " +
                    "이미지 타입은 `availableDrinks` 또는 `menuImages`로 구분되며, 추가할 이미지는 `newImageUrls` 리스트에 제공되고, 삭제할 이미지는 `removeImageIds` 리스트에 제공됩니다." +
                    "요청 시 `JWT 토큰`을 입력하면 Gateway에서 자동으로 `owner-id`를 추출하여 헤더에 추가하므로, 별도로 포함할 필요 없습니다."
    )
    ApplicationResponse<String> updateStoreImages(
            @Parameter(description = "조회할 제휴 업체의 고유 ID", required = true, example = "1") @PathVariable(value = "storeId", required = true)  Long storeId,
            @Parameter(description = "조회할 제휴 업체의 고유 ID", required = true, example = "1") @RequestHeader(value = "owner-id", required = false) Long ownerId,
            @Valid @RequestBody StoreImageUpdateRequest request
    );
}
