package com.drinkhere.drinklystore.common.exception.event;

import com.drinkhere.drinklystore.common.exception.BaseErrorCode;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum EventErrorCode implements BaseErrorCode {
    EVENT_NOT_FOUND("해당 ID값을 가진 이벤트를 찾을 수 없습니다. 다시 조회해주세요.", 8001, HttpStatus.NOT_FOUND),
    EXTERNAL_EVENT_NOT_FOUND("해당 ID값을 가진 외부 이벤트(행사/할인)를 찾을 수 없습니다. 다시 조회해주세요.", 8002, HttpStatus.NOT_FOUND),
    ;

    private final String message;
    private final int errorCode;
    private final HttpStatus httpStatus;

    @Override
    public ApplicationResponse<String> toResponseEntity() {
        return ApplicationResponse.server(message);
    }
}
