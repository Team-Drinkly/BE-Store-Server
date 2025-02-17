package com.drinkhere.drinklystore.application.presentation.docs;

import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequest;
import com.drinkhere.drinklystore.domain.dto.response.GetFreeDrinkHistoryResponse;

import java.util.List;

public interface FreeDrinkAdminControllerDocs {
    ApplicationResponse<List<GetFreeDrinkHistoryResponse>> getFreeDrinkHistories(Long storeId, Long ownerId);
}