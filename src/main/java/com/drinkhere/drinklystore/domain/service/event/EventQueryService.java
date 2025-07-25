package com.drinkhere.drinklystore.domain.service.event;

import com.drinkhere.drinklystore.common.annotation.DomainService;
import com.drinkhere.drinklystore.common.exception.event.EventException;
import com.drinkhere.drinklystore.domain.dto.event.response.GetEventResponse;
import com.drinkhere.drinklystore.domain.dto.event.response.GetEventsResponse;
import com.drinkhere.drinklystore.domain.entity.event.Event;
import com.drinkhere.drinklystore.domain.entity.event.ExternalEvent;
import com.drinkhere.drinklystore.domain.repository.EventRepository;
import com.drinkhere.drinklystore.domain.repository.ExternalEventRepository;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.drinkhere.drinklystore.common.exception.event.EventErrorCode.EVENT_NOT_FOUND;
import static com.drinkhere.drinklystore.common.exception.event.EventErrorCode.EXTERNAL_EVENT_NOT_FOUND;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventQueryService {
    private final EventRepository eventRepository;
    private final ExternalEventRepository externalEventRepository;

    /**
     **************************** 자체 이벤트 ****************************
     */
    public Event findById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new EventException(EVENT_NOT_FOUND));
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Event findWithImagesByIdOrThrow(Long eventId) {
        return eventRepository.findWithImagesByIdOrThrow(eventId);
    }


    /**
     **************************** 외부 이벤트 ****************************
     */
    public ExternalEvent findExternalEventById(Long externalEventId) {
        return externalEventRepository.findById(externalEventId)
                .orElseThrow(() -> new EventException(EXTERNAL_EVENT_NOT_FOUND));
    }

    public List<ExternalEvent> findAllExternalEvents() { return externalEventRepository.findAll(); }
}
