package com.drinkhere.drinklystore.common.exception.event;

import com.drinkhere.drinklystore.common.exception.BaseErrorCode;
import com.drinkhere.drinklystore.common.exception.CustomException;

public class EventException extends CustomException {
    public EventException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
