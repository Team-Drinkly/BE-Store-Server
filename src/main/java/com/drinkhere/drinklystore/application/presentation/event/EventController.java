package com.drinkhere.drinklystore.application.presentation.event;

import com.drinkhere.drinklystore.application.service.Impl.event.CreateEventUseCase;
import com.drinkhere.drinklystore.application.service.Impl.event.GetEventUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.event.response.GetEventResponse;
import com.drinkhere.drinklystore.domain.dto.event.response.GetEventsResponse;
import com.drinkhere.drinklystore.domain.dto.event.response.GetExternalEventsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event/m")
@RequiredArgsConstructor
public class EventController {
    private final GetEventUseCase getEventUseCase;

    @GetMapping
    public ApplicationResponse<List<GetEventsResponse>> getEvents() {
        return ApplicationResponse.ok(getEventUseCase.getEvents(), "전체 이벤트 목록 조회에 성공했습니다.");
    }

    @GetMapping("/{eventId}")
    public ApplicationResponse<GetEventResponse> getEvent(@PathVariable Long eventId) {
        return ApplicationResponse.ok(getEventUseCase.getEvent(eventId), "이벤트 상세 조회에 성공했습니다.");
    }

    @GetMapping("/external")
    public ApplicationResponse<List<GetExternalEventsResponse>> getExternalEvents() {
        return ApplicationResponse.ok(getEventUseCase.getExternalEvents(), "전체 외부 이벤트 목록 조회에 성공했습니다.");
    }
}
