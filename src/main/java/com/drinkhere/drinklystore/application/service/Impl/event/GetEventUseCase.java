package com.drinkhere.drinklystore.application.service.Impl.event;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.service.event.EventQueryService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class GetEventUseCase {
    private final EventQueryService eventQueryService;

    public void getEvents() {

    }

    public void getEvent() {

    }
}
