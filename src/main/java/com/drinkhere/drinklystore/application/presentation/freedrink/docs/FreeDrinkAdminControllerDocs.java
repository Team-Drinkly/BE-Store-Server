package com.drinkhere.drinklystore.application.presentation.freedrink.docs;

import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.response.GetFreeDrinkHistoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "4. 드링클리 멤버쉽 사용 이력 READ 관련 API", description = "드링클리 멤버쉽 사용 이력 READ 관련 API 명세입니다.")
public interface FreeDrinkAdminControllerDocs {

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
            description = "특정 제휴 업체에서 사용된 멤버쉽 사용 이력을 조회합니다.  요청 시 `JWT 토큰`을 입력하면 Gateway에서 자동으로 `owner-id`를 추출하여 헤더에 추가하므로, 별도로 포함할 필요 없습니다."
    )
    ApplicationResponse<List<GetFreeDrinkHistoryResponse>> getFreeDrinkHistories(
            @Parameter(description = "제휴 업체의 ID (PathVariable)", example = "1") @PathVariable(value = "storeId", required = true) Long storeId,
            @Parameter(description = "제휴 업체 사장님의 ID (Header)", example = "1") @RequestHeader(value = "owner-id", required = false) Long ownerId
    );
}