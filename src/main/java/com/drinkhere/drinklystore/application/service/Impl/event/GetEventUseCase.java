package com.drinkhere.drinklystore.application.service.Impl.event;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.event.response.GetEventResponse;
import com.drinkhere.drinklystore.domain.dto.event.response.GetEventsResponse;
import com.drinkhere.drinklystore.domain.entity.event.Event;
import com.drinkhere.drinklystore.domain.service.event.EventQueryService;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationService
@RequiredArgsConstructor
public class GetEventUseCase {
    private final EventQueryService eventQueryService;
    private final PresignedUrlService presignedUrlService;

    public List<GetEventsResponse> getEvents() {
        return eventQueryService.findAllEvents();
    }

    public GetEventResponse getEvent(Long eventId) {
        Event event = eventQueryService.findWithImagesByIdOrThrow(eventId);
        return GetEventResponse.toDto(event, presignedUrlService);
    }
}
