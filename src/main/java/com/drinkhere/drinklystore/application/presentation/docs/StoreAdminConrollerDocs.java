package com.drinkhere.drinklystore.application.presentation.docs;


import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.RegisterStoreRequest;

public interface StoreAdminConrollerDocs {
    ApplicationResponse<String> registerStore(RegisterStoreRequest request);
    ApplicationResponse<String> updateStore();
}
