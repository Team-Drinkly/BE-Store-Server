package com.drinkhere.drinklystore.application.presentation.freedrink.docs;

import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequest;

public interface FreeDrinkControllerDocs {
    ApplicationResponse<String> createFreeDrinkHistory(
            Long memberId,
            Long subscriberId,
            CreateFreeDrinkHistoryRequest createFreeDrinkHistoryRequest
    );
}
