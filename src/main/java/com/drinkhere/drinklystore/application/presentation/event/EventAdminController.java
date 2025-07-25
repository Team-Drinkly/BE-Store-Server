package com.drinkhere.drinklystore.application.presentation.event;

import com.drinkhere.drinklystore.application.service.Impl.event.CreateEventUseCase;
import com.drinkhere.drinklystore.application.service.Impl.event.DeleteEventUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.domain.dto.event.request.CreateEventRequest;
import com.drinkhere.drinklystore.domain.dto.event.request.CreateExternalEventRequest;
import com.drinkhere.drinklystore.domain.dto.event.response.CreateEventResponse;
import com.drinkhere.drinklystore.domain.dto.event.response.CreateExternalEventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event/o")
@RequiredArgsConstructor
public class EventAdminController {
    private final CreateEventUseCase createEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;

    /**
     * 드링클리 자체 내부 이벤트 관리 API
     */
    @PostMapping("/create")
    public ApplicationResponse<CreateEventResponse> createEvent(@RequestBody CreateEventRequest request) {
        return ApplicationResponse.created(createEventUseCase.createEvent(request), "이벤트 등록이 성공적으로 처리됐습니다.");
    }

    @DeleteMapping("/delete")
    public ApplicationResponse<String> deleteEvent(@RequestHeader Long eventId) {
        deleteEventUseCase.deleteEvent(eventId);
        return ApplicationResponse.ok("이벤트 삭제가 성공적으로 처리됐습니다.");
    }

    /**
     * 외부 이벤트(축제/행사/할인/해택) 관리 API
     */
    @PostMapping("/external/create")
    public ApplicationResponse<CreateExternalEventResponse> createExternalEvent(@RequestBody CreateExternalEventRequest request) {
        return ApplicationResponse.created(createEventUseCase.createExternalEvent(request), "이벤트 등록이 성공적으로 처리됐습니다.");
    }

    @DeleteMapping("/external/delete")
    public ApplicationResponse<String> deleteExternalEvent(@RequestHeader Long externalEventId) {
        deleteEventUseCase.deleteExternalEvent(externalEventId);
        return ApplicationResponse.ok("이벤트 삭제가 성공적으로 처리됐습니다.");
    }
}
