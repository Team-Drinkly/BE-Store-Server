package com.drinkhere.drinklystore.domain.dto.event.request;

import com.drinkhere.drinklystore.domain.entity.event.ExternalEvent;

import java.time.LocalDateTime;

public record CreateExternalEventRequest(
        String thumbnailPath,
        String title,
        String organizer,
        String description,
        String redirectUrl,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
    public ExternalEvent toEntity() {
        return ExternalEvent.builder()
                .thumbnailPath(thumbnailPath)
                .title(title)
                .organizer(organizer)
                .description(description)
                .redirectUrl(redirectUrl)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
