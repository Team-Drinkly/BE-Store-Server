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

    /**
     **************************** 자체 이벤트 ****************************
     */
    public void deleteEvent(Long eventId) {
        eventCommandService.deleteEvent(eventQueryService.findById(eventId));
    }

    /**
     **************************** 외부 이벤트 ****************************
     */
    public void deleteExternalEvent(Long externalEventId) {
        eventCommandService.deleteExternalEvent(eventQueryService.findExternalEventById(externalEventId));
    }
}
