package com.drinkhere.drinklystore.common.exception.store;

import com.drinkhere.drinklystore.common.exception.BaseErrorCode;
import com.drinkhere.drinklystore.common.exception.CustomException;

public class StoreException extends CustomException {
    public StoreException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
