package com.drinkhere.drinklystore.common.exception;


import com.drinkhere.drinklystore.common.exception.event.EventException;
import com.drinkhere.drinklystore.common.exception.store.StoreException;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * Controller Layer에서 발생하는 예외를 처리하는 것이지
 * Filter 단의 예외는 인식하지 못한다...!
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) // @Valid 검증 실패 처리
    public ApplicationResponse<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getField())
                    .append(": ")
                    .append(fieldError.getDefaultMessage())
                    .append(" & ");
        }

        return ApplicationResponse.badRequest(errorMessage.delete(errorMessage.length()-3, errorMessage.length()).toString());
    }

    @ExceptionHandler(StoreException.class)
    protected ApplicationResponse<String> handleVendorException(StoreException e) {
        return ApplicationResponse.custom(
                e.getMessage(), // Payload
                e.getErrorCode().getHttpStatus().value(), // Code
                e.getErrorCode().getHttpStatus().getReasonPhrase() // Message
        );
    }

    @ExceptionHandler(EventException.class)
    protected ApplicationResponse<String> handleEventException(EventException e) {
        return ApplicationResponse.custom(
                e.getMessage(), // Payload
                e.getErrorCode().getHttpStatus().value(), // Code
                e.getErrorCode().getHttpStatus().getReasonPhrase() // Message
        );
    }

    @ExceptionHandler(RuntimeException.class)
    protected ApplicationResponse<Void> handleRuntimeException(RuntimeException e) {
        return ApplicationResponse.server(null, e.getMessage());
    }
}
