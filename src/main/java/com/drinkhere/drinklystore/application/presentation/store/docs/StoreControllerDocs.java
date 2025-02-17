package com.drinkhere.drinklystore.application.presentation.store.docs;


import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.response.GetStoresByLocationResponse;
import com.drinkhere.drinklystore.domain.dto.response.GetStoreResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "2. 제휴 업체 조회 관련 API", description = "제휴 업체 정보 조회관련 API 명세입니다.")
public interface StoreControllerDocs {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST -  잘못된 요청", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - 서버 오류", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class)))
    })
    @Operation(
            summary = "지도 뷰 및 리스트 뷰 제휴 업체 조회 API",
            description = "주어진 위도와 경도 그리고 반경(KM)을 기준으로 제휴 업체 목록을 조회하는 API입니다. \n" +
                    "사용자는 위치(`latitude`, `longitude`)와 검색 키워드(`searchKeyword`)를 제공하여 주변의 제휴 업체들을 필터링하고, 특정 반경 내(`radius`)의 업체들을 조회할 수 있습니다. " +
                    "만약 검색 키워드가 제공되면, 해당 키워드로 필터링된 업체들만 반환됩니다. 반경 값은 키로미터 단위로 제공되며, 기본적으로 가까운 순서대로 제휴 업체들이 반환됩니다."
    )
    ApplicationResponse<List<GetStoresByLocationResponse>> getStoresByLocation(
            @Parameter(description = "위도 값", example = "37.63022195215973") double latitude,
            @Parameter(description = "경도 값", example = "127.07671771357782") double longitude,
            @Parameter(description = "검색할 반경 값 (KM 단위)", example = "50") int radius,
            @Parameter(description = "검색할 키워드 (옵션)", example = "여기, 꼬치네") String searchKeyword
    );


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - 잘못된 요청", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - 제휴 업체를 찾을 수 없음", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - 서버 오류", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class)))
    })
    @Operation(summary = "제휴 업체 상세 조회 API", description = "제휴 업체의 상세 정보를 조회합니다. `storeId`에 해당하는 제휴 업체의 정보를 반환하며, 존재하지 않는 `storeId`를 입력할 경우 404 에러를 반환합니다.")
    ApplicationResponse<GetStoreResponse> getStore(
            @Parameter(description = "조회할 제휴 업체의 고유 ID", required = true, example = "1") Long storeId
    );
}
