package com.drinkhere.drinklystore.application.presentation.docs;


import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.response.GetStoresByLocationResponse;
import com.drinkhere.drinklystore.domain.dto.response.GetStoreResponse;

import java.util.List;

public interface StoreControllerDocs {

    ApplicationResponse<List<GetStoresByLocationResponse>> getStoresByLocation(double Latitude, double Longitude, int radius, String searchKeyword);


    ApplicationResponse<GetStoreResponse> getStore(Long storeId);

}
