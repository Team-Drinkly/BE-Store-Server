package com.drinkhere.drinklystore.domain.service.event;

import com.drinkhere.drinklystore.common.annotation.DomainService;
import com.drinkhere.drinklystore.common.exception.event.EventException;
import com.drinkhere.drinklystore.domain.entity.event.Event;
import com.drinkhere.drinklystore.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static com.drinkhere.drinklystore.common.exception.event.EventErrorCode.EVENT_NOT_FOUND;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventQueryService {
    private final EventRepository eventRepository;

    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventException(EVENT_NOT_FOUND));
    }
}
