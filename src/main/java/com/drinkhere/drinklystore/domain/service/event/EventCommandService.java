package com.drinkhere.drinklystore.domain.service.event;

import com.drinkhere.drinklystore.common.annotation.DomainService;
import com.drinkhere.drinklystore.domain.dto.event.request.CreateEventRequest;
import com.drinkhere.drinklystore.domain.dto.event.request.CreateExternalEventRequest;
import com.drinkhere.drinklystore.domain.dto.event.response.CreateEventResponse;
import com.drinkhere.drinklystore.domain.dto.event.response.CreateExternalEventResponse;
import com.drinkhere.drinklystore.domain.entity.event.Event;
import com.drinkhere.drinklystore.domain.entity.event.EventImage;
import com.drinkhere.drinklystore.domain.entity.event.ExternalEvent;
import com.drinkhere.drinklystore.domain.repository.EventImageRepository;
import com.drinkhere.drinklystore.domain.repository.EventRepository;
import com.drinkhere.drinklystore.domain.repository.ExternalEventRepository;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@DomainService
@RequiredArgsConstructor
@Transactional
public class EventCommandService {

    private final EventRepository eventRepository;
    private final EventImageRepository eventImageRepository;
    private final ExternalEventRepository externalEventRepository;
    private final PresignedUrlService presignedUrlService;

    /**
     **************************** 자체 이벤트 ****************************
     */
    public CreateEventResponse createEvent(CreateEventRequest request) {
        // 1. Event 저장
        Event savedEvent = eventRepository.save(request.toEntity());

        // 2. EventImage 저장
        List<EventImage> images = request.eventImagePaths().stream()
                .map(filePath -> EventImage.builder()
                        .event(savedEvent)
                        .eventImageUrl(filePath)
                        .build())
                .toList();

        eventImageRepository.saveAll(images);

        // 3. 응답 반환
        return CreateEventResponse.toDto(savedEvent, images, presignedUrlService);
    }

    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    /**
     **************************** 외부 이벤트 ****************************
     */
    public CreateExternalEventResponse createExternalEvent(CreateExternalEventRequest request) {
        ExternalEvent savedExternalEvent = externalEventRepository.save(request.toEntity());
        return CreateExternalEventResponse.toDto(savedExternalEvent, presignedUrlService);
    }

    public void deleteExternalEvent(ExternalEvent externalEvent) { externalEventRepository.delete(externalEvent); }
}