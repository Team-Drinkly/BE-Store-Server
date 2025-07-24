package com.drinkhere.drinklystore.application.service.Impl.event;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.domain.dto.event.response.GetEventResponse;
import com.drinkhere.drinklystore.domain.dto.event.response.GetEventsResponse;
import com.drinkhere.drinklystore.domain.entity.event.Event;
import com.drinkhere.drinklystore.domain.service.event.EventQueryService;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@ApplicationService
@RequiredArgsConstructor
public class GetEventUseCase {
    private final EventQueryService eventQueryService;
    private final PresignedUrlService presignedUrlService;

    public List<GetEventsResponse> getEvents() {
        List<Event> allEvents = eventQueryService.findAllEvents();

        LocalDateTime now = LocalDateTime.now();

        // 진행 중인 이벤트: 시작 <= 현재 <= 종료
        List<GetEventsResponse> ongoingEvents = allEvents.stream()
                .filter(event -> !event.getStartDate().isAfter(now) && !event.getEndDate().isBefore(now))
                .sorted(Comparator.comparing(Event::getEndDate)) // 종료일 가까운 순
                .map(event -> GetEventsResponse.toDto(event, presignedUrlService))
                .toList();

        // 마감된 이벤트: 종료일 < 현재
        List<GetEventsResponse> closedEvents = allEvents.stream()
                .filter(event -> event.getEndDate().isBefore(now))
                .sorted(Comparator.comparing(Event::getEndDate).reversed()) // 최근 마감일 위로
                .map(event -> GetEventsResponse.toDto(event, presignedUrlService))
                .toList();

        // 합쳐서 반환
        List<GetEventsResponse> result = new ArrayList<>();
        result.addAll(ongoingEvents);
        result.addAll(closedEvents);

        return result;
    }

    public GetEventResponse getEvent(Long eventId) {
        Event event = eventQueryService.findWithImagesByIdOrThrow(eventId);
        return GetEventResponse.toDto(event, presignedUrlService);
    }
}
