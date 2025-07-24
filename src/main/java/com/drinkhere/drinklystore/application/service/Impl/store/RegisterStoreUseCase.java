package com.drinkhere.drinklystore.application.service.Impl.store;

import com.drinkhere.drinklystore.clientgeocoding.dto.Coordinates;
import com.drinkhere.drinklystore.clientgeocoding.service.GeocodingUseCase;
import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.request.RegisterStoreRequest;
import com.drinkhere.drinklystore.domain.dto.response.StoreResponse;
import com.drinkhere.drinklystore.domain.entity.store.Store;
import com.drinkhere.drinklystore.domain.service.store.StoreCommandService;
import com.drinkhere.drinklystore.domain.service.store.StoreQueryService;
import com.drinkhere.drinklystore.events.signup.event.StoreRegisterEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@ApplicationService
@RequiredArgsConstructor
public class RegisterStoreUseCase {
    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;
    private final GeocodingUseCase geocodingUseCase;
    private final ApplicationEventPublisher eventPublisher;


    public StoreResponse registerStore(RegisterStoreRequest request) {
        // 1. 주소로 좌표 얻기
        Coordinates coordinates = geocodingUseCase.getCoordinates(request.storeAddress());

        // 2. 매장 저장
        Store savedStore = storeCommandService.save(request.toEntity(coordinates));

        // 3. Slack Webhook
        eventPublisher.publishEvent(new StoreRegisterEvent(this, "owner", savedStore.getId(), request.storeName(), request.storeTel()));

        // 4. DTO 응답 반환
        return StoreResponse.toDto(savedStore, null, null, null);
    }

}
