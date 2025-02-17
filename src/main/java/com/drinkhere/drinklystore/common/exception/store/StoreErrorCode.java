package com.drinkhere.drinklystore.common.exception.store;

import com.drinkhere.drinklystore.common.exception.BaseErrorCode;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {
    STORE_NOT_FOUND("해당 ID값을 가진 제휴업체를 찾을 수 없습니다. 다시 조회해주세요.", 2001, HttpStatus.NOT_FOUND),

    ;

    private final String message;
    private final int errorCode;
    private final HttpStatus httpStatus;

    @Override
    public ApplicationResponse<String> toResponseEntity() {
        return ApplicationResponse.server(message);
    }
}
