package com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.freedrink.response.GetFreeDrinkHistoriesResponse;
import com.drinkhere.drinklystore.domain.dto.freedrink.response.GetFreeDrinkHistoriesResponseV2;
import com.drinkhere.drinklystore.domain.dto.freedrink.response.GetMemberFreeDrinkHistoryResponse;
import com.drinkhere.drinklystore.domain.entity.store.FreeDrinkHistory;
import com.drinkhere.drinklystore.domain.entity.store.StoreImage;
import com.drinkhere.drinklystore.domain.service.freedrinkhistory.FreeDrinkHistoryQueryService;
import com.drinkhere.drinklystore.domain.service.store.StoreQueryService;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import com.drinkhere.drinklystore.util.TimeUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationService
@RequiredArgsConstructor
public class GetMemberFreeDrinkHistoriesUseCase {

    private final FreeDrinkHistoryQueryService freeDrinkHistoryQueryService;
    private final PresignedUrlService presignedUrlService;
    private final StoreQueryService storeQueryService;

    public List<GetMemberFreeDrinkHistoryResponse> getMemberFreeDrinkHistory(Long memberId, Long subscribeId) {
        List<FreeDrinkHistory> freeDrinkHistoriesByMemberId = freeDrinkHistoryQueryService.getMemberFreeDrinkHistories(memberId, subscribeId);

        return freeDrinkHistoriesByMemberId.stream()
                .map(GetMemberFreeDrinkHistoryResponse::toDto)
                .collect(Collectors.toList());
    }

    public GetFreeDrinkHistoriesResponse getFreeDrinkHistories(Long memberId, Long subscribeId) {
        List<FreeDrinkHistory> histories = freeDrinkHistoryQueryService.getFreeDrinkHistoriesAtMemberApp(memberId);

        int usedCount = (int) histories.stream()
                .filter(history -> history.getSubscribeId().equals(subscribeId))
                .count();

        List<GetFreeDrinkHistoriesResponse.DrinkHistory> drinksHistory = histories.stream()
                .map(history -> new GetFreeDrinkHistoriesResponse.DrinkHistory(
                        history.getId(),
                        history.getStore().getStoreName(),
                        history.getProvidedDrink(),
                        TimeUtil.refineToFullKoreanDateTime(history.getCreatedDate())
                ))
                .toList();

        return new GetFreeDrinkHistoriesResponse(usedCount, drinksHistory);
    }

    public GetFreeDrinkHistoriesResponseV2 getFreeDrinkHistoriesV2(Long memberId, Long subscribeId) {
        List<FreeDrinkHistory> histories = freeDrinkHistoryQueryService.getFreeDrinkHistoriesAtMemberApp(memberId);

        int usedCount = (int) histories.stream()
                .filter(history -> history.getSubscribeId().equals(subscribeId))
                .count();

        List<GetFreeDrinkHistoriesResponseV2.DrinkHistory> drinksHistory = histories.stream()
                .map(history ->
                        new GetFreeDrinkHistoriesResponseV2.DrinkHistory(
                            history.getId(),
                            history.getStore().getStoreName(),
                            history.getProvidedDrinkImageId(),
                            history.getProvidedDrink(),
                            TimeUtil.refineToFullKoreanDateTime(history.getCreatedDate())
                        )
                )
                .toList();

        return new GetFreeDrinkHistoriesResponseV2(usedCount, drinksHistory);
    }


    public String getFreeDrinkImageUrl(Long imageId) {
        StoreImage storeImageByIdOrThrow = storeQueryService.findStoreImageByIdOrThrow(imageId);
        return presignedUrlService.getPresignedUrlForGet(storeImageByIdOrThrow.getStoreImageUrl());
    }

}
