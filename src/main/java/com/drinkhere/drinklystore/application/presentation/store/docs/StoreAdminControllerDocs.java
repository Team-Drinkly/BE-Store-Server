package com.drinkhere.drinklystore.application.presentation.store.docs;


import com.drinkhere.drinklystore.common.exception.ErrorResponse;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.RegisterStoreRequest;
import com.drinkhere.drinklystore.domain.dto.request.StoreImageUpdateRequest;
import com.drinkhere.drinklystore.domain.dto.request.UpdateStoreRequest;
import com.drinkhere.drinklystore.domain.dto.response.*;
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

import java.util.List;

@Tag(name = "1. 제휴 업체 사장님 앱 관련 API", description = "제휴 업체 사장님 앱 관련 API 명세입니다.")
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
    @Operation(summary = "제휴 업체 업데이트 API",
            description = "제휴 업체 정보를 업데이트합니다.이용가능한 주류와 메뉴 정보(이미지 및 설명)는 별개의 API로 빠져있습니다.\n" +
                    "요청 시 `JWT 토큰`을 입력하면 Gateway에서 자동으로 `owner-id`를 추출하여 헤더에 추가하므로, 별도로 포함할 필요 없습니다."
    )
    ApplicationResponse<GetStoreResponse> updateStore(
            @Parameter(description = "제휴 업체의 고유 ID", required = true, example = "1") @PathVariable Long storeId,
            @Parameter(description = "조회할 제휴 업체의 고유 ID", required = false, example = "1") @RequestHeader(value = "owner-id", required = false) Long ownerId,
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
    ApplicationResponse<StoreResponse> updateStoreImages(
            @Parameter(description = "조회할 제휴 업체의 고유 ID", required = true, example = "1") @PathVariable(value = "storeId", required = true)  Long storeId,
            @Parameter(description = "조회할 제휴 업체 사장님의 고유 ID", required = false, example = "1") @RequestHeader(value = "owner-id", required = false) Long ownerId,
            @Valid @RequestBody StoreImageUpdateRequest request
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - 유효하지 않은 요청", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - 서버 오류", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class)))
    })
    @Operation(summary = "사장님이 등록한 제휴 업체 목록 조회 API",
            description = "사장님이 등록한 제휴 업체 리스트를 반환합니다. " +
                    "요청 시 `JWT 토큰`을 입력하면 Gateway에서 자동으로 `owner-id`를 추출하여 헤더에 추가하므로, 별도로 포함할 필요 없습니다.")
    ApplicationResponse<List<GetStoreListResponse>> getStoreList(
            @Parameter(description = "사장님의 고유 ID", required = false, example = "1") @RequestHeader(value = "owner-id", required = false) Long ownerId
    );



    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - 유효하지 않은 요청", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found - 존재하지 않는 storeId", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - 서버 오류", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class)))
    })
    @Operation(summary = "제휴 업체 상세 정보 및 최근 멤버십 사용 이력 조회 API",
            description = "특정 제휴 업체의 정보와 해당 제휴 업체에서 일반 멤버들이 사용한 최근 멤버십 사용 내역을 반환합니다. " +
                    "요청 시 `JWT 토큰`을 입력하면 Gateway에서 자동으로 `owner-id`를 추출하여 헤더에 추가하므로, 별도로 포함할 필요 없습니다.")
    ApplicationResponse<GetOwnerMainPageResponse> getOwnerMainPage(
            @Parameter(description = "조회할 제휴 업체의 고유 ID", required = true, example = "1")  @PathVariable Long storeId,
            @Parameter(description = "사장님의 고유 ID", required = false, example = "1") @RequestHeader(value = "owner-id", required = false) Long ownerId
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - 잘못된 요청", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - 서버 오류", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class)))
    })
    @Operation(
            summary = "멤버쉽 사용 이력 조회 API",
            description = "특정 제휴 업체에서 사용된 멤버쉽 사용 이력 전체를 조회합니다.  " +
                    "요청 시 `JWT 토큰`을 입력하면 Gateway에서 자동으로 `owner-id`를 추출하여 헤더에 추가하므로, 별도로 포함할 필요 없습니다."
    )
    ApplicationResponse<List<GetFreeDrinkHistoryResponse>> getFreeDrinkHistories(
            @Parameter(description = "제휴 업체의 ID (PathVariable)", example = "1") @PathVariable(value = "storeId", required = true) Long storeId,
            @Parameter(description = "제휴 업체 사장님의 ID (Header)", example = "1") @RequestHeader(value = "owner-id", required = false) Long ownerId
    );
}
