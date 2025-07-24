package com.drinkhere.drinklystore.domain.service.event;

import com.drinkhere.drinklystore.common.annotation.DomainService;
import com.drinkhere.drinklystore.domain.dto.event.request.CreateEventRequest;
import com.drinkhere.drinklystore.domain.dto.event.response.CreateEventResponse;
import com.drinkhere.drinklystore.domain.entity.event.Event;
import com.drinkhere.drinklystore.domain.entity.event.EventImage;
import com.drinkhere.drinklystore.domain.repository.EventImageRepository;
import com.drinkhere.drinklystore.domain.repository.EventRepository;
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
    private final PresignedUrlService presignedUrlService;

    public CreateEventResponse createEvent(CreateEventRequest request) {
        // 1. Event 저장
        Event event = Event.builder()
                .thumbnailPath(request.thumbnailPath())
                .title(request.title())
                .benefit(request.benefit())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .description(request.description())
                .redirectUrl(request.redirectUrl())
                .eventCategory(request.eventCategory())
                .build();

        Event savedEvent = eventRepository.save(event);

        // 2. EventImage 저장
        AtomicInteger index = new AtomicInteger(0);
        List<EventImage> images = request.eventImagePaths().stream()
                .map(filePath -> EventImage.builder()
                        .event(savedEvent)
                        .eventImageUrl(filePath)
                        .build())
                .toList();

        eventImageRepository.saveAll(images);

        // 3. 응답 반환
        return CreateEventResponse.toDto(event, images, presignedUrlService);
    }
}