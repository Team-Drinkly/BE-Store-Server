package com.drinkhere.drinklystore.application.service.Impl.event;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.event.request.CreateEventRequest;
import com.drinkhere.drinklystore.domain.dto.event.request.CreateExternalEventRequest;
import com.drinkhere.drinklystore.domain.dto.event.response.CreateEventResponse;
import com.drinkhere.drinklystore.domain.dto.event.response.CreateExternalEventResponse;
import com.drinkhere.drinklystore.domain.service.event.EventCommandService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class CreateEventUseCase {
    private final EventCommandService eventCommandService;

    public CreateEventResponse createEvent(CreateEventRequest createEventRequest) {
        return eventCommandService.createEvent(createEventRequest);
    }

    public CreateExternalEventResponse createExternalEvent(CreateExternalEventRequest request) {
        return eventCommandService.createExternalEvent(request);
    }
}
