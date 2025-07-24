package com.drinkhere.drinklystore.application.service.Impl.event;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.service.event.EventCommandService;
import com.drinkhere.drinklystore.domain.service.event.EventQueryService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class DeleteEventUseCase {
    private final EventCommandService eventCommandService;
    private final EventQueryService eventQueryService;

    public void deleteEvent(Long eventId) {
        eventCommandService.deleteEvent(eventQueryService.findById(eventId));
    }
}
