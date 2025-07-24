package com.drinkhere.drinklystore.application.presentation.event;

import com.drinkhere.drinklystore.application.service.Impl.event.CreateEventUseCase;
import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event/m")
@RequiredArgsConstructor
public class EventController {
}
