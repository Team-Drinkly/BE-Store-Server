package com.drinkhere.drinklystore.domain.service.event;

import com.drinkhere.drinklystore.common.annotation.DomainService;
import com.drinkhere.drinklystore.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class EventCommandService {
    private final EventRepository eventRepository;
}
