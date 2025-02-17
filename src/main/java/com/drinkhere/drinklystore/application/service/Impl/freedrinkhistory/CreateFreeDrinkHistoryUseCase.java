package com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequest;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.service.freedrinkhistory.FreeDrinkHistoryCommandService;
import com.drinkhere.drinklystore.domain.service.store.StoreQueryService;
import com.drinkhere.drinklystore.openfeign.client.MemberClient;
import com.drinkhere.drinklystore.openfeign.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class CreateFreeDrinkHistoryUseCase {
    private final FreeDrinkHistoryCommandService freeDrinkHistoryCommandService;
    private final StoreQueryService storeQueryService;
    private final MemberClient memberClient;

    public void createFreeDrinkHistory(Long memberId, Long subscribeId, CreateFreeDrinkHistoryRequest createFreeDrinkHistoryRequest) {
        Store store = storeQueryService.findById(createFreeDrinkHistoryRequest.storeId());

        // Feign Client 서버 간 통신으로 member nickname 들고옴.
        MemberResponse memberResponse = memberClient.getMemberById(memberId);
        String memberNickname = memberResponse.getNickname();

        freeDrinkHistoryCommandService.createFreeDrinkHistory(memberId, memberNickname, subscribeId, store, createFreeDrinkHistoryRequest.providedDrink());
    }

}
