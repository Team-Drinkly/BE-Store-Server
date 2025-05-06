package com.drinkhere.drinklystore.application.presentation.freedrink.docs;

import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequest;
import com.drinkhere.drinklystore.domain.dto.response.GetMemberFreeDrinkHistoryResponse;
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

@Tag(name = "3. 드링클리 멤버쉽 사용 이력 관련 API", description = "드링클리 멤버쉽 사용 이력 관련 API 명세입니다.")
public interface FreeDrinkControllerDocs {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST -  잘못된 요청", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED -  구독하지 않은 회원", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - 서버 오류", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class)))
    })
    @Operation(
            summary = "멤버쉽 사용 이력 생성 API",
            description = "멤버쉽 사용 이력을 생성합니다. 요청 시 `JWT 토큰`을 입력하면 Gateway에서 자동으로 `member-id`, `is_subscribe` 및 `subscribe-id`를 추출하여 헤더에 추가하므로, 별도로 포함할 필요 없습니다." +
                    "구독된 회원만 사용가능합니다."
    )
    ApplicationResponse<String> createFreeDrinkHistory(
            @Parameter(description = "드링클리 멤버의 ID (Header)", example = "1") @RequestHeader(value = "member-id", required = false) Long memberId,
            @Parameter(description = "구독 이력 ID (Header)", example = "1") @RequestHeader(value = "subscribe-id", required = false) Long subscriberId,
            @Parameter(description = "멤버 구독 여부 (Header)", example = "true") @RequestHeader(value = "is-subscribe", required = false) String isSubscribe,
            @Valid @RequestBody CreateFreeDrinkHistoryRequest createFreeDrinkHistoryRequest
    );

    @Operation(
            summary = "특정 멤버의 멤버쉽 사용 이력 조회 Feign Client API(프론트 작업할 필요 X)",
            description = "특정 멤버가 특정 구독(subscribeId)과 관련하여 사용한 멤버십 무료 음료 이력을 조회합니다."
    )
    ApplicationResponse<List<GetMemberFreeDrinkHistoryResponse>> getMemberFreeDrinkHistory(
            @PathVariable Long memberId,
            @PathVariable Long subscribeId
    );
}
