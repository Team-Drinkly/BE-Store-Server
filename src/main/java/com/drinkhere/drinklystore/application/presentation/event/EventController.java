package com.drinkhere.drinklystore.application.presentation.event;

import com.drinkhere.drinklystore.application.service.Impl.event.CreateEventUseCase;
import com.drinkhere.drinklystore.application.service.Impl.event.GetEventUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event/m")
@RequiredArgsConstructor
public class EventController {
    private final GetEventUseCase getEventUseCase;

    @GetMapping
    public ApplicationResponse<String> getEvents() {
        return ApplicationResponse.ok( "전체 이벤트 목록 조회에 성공했습니다.");
    }

//    @GetMapping("/{eventId}")
//    public List<> getEvent(@PathVariable Long eventId) {
//        ApplicationResponse.ok(getEvent(eventId), "이벤트 상세 조회에 성공했습니다.");
//    }
}
