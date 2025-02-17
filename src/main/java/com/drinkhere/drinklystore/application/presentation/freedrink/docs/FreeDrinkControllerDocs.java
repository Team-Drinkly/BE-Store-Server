package com.drinkhere.drinklystore.application.presentation.freedrink.docs;

import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "3. 드링클리 멤버쉽 사용 이력 CREATE 관련 API", description = "드링클리 멤버쉽 사용 이력 CREATE 관련 API 명세입니다.")
public interface FreeDrinkControllerDocs {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST -  잘못된 요청", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - 서버 오류", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationResponse.class)))
    })
    @Operation(summary = "멤버쉽 사용 이력 생성 API", description = "멤버쉽 사용 이력 생성을 생성합니다.")
    ApplicationResponse<String> createFreeDrinkHistory(
            @Valid @RequestHeader(value = "member-id", required = false) Long memberId,
            @Valid @RequestHeader(value = "subscribe-id", required = false) Long subscriberId,
            @Valid @RequestBody CreateFreeDrinkHistoryRequest createFreeDrinkHistoryRequest
    );
}
