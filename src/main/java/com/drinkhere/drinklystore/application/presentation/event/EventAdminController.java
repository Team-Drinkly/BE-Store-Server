package com.drinkhere.drinklystore.application.presentation.event;

import com.drinkhere.drinklystore.application.service.Impl.event.CreateEventUseCase;
import com.drinkhere.drinklystore.application.service.Impl.event.DeleteEventUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.event.request.CreateEventRequest;
import com.drinkhere.drinklystore.domain.dto.event.response.CreateEventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/store/o/event")
@RequiredArgsConstructor
public class EventAdminController {
    private final CreateEventUseCase createEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;

    @PostMapping
    public ApplicationResponse<CreateEventResponse> createEvent(@RequestBody CreateEventRequest createEventRequest) {
        return ApplicationResponse.created(createEventUseCase.createEvent(createEventRequest), "이벤트 등록이 성공적으로 처리됐습니다.");
    }


    @DeleteMapping
    public ApplicationResponse<String> createEvent(
            @RequestHeader Long eventId
    ) {
        deleteEventUseCase.deleteEvent(eventId);
        return ApplicationResponse.ok("이벤트 삭제가 성공적으로 처리됐습니다.");
    }
}
