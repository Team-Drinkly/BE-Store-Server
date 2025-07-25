package com.drinkhere.drinklystore.domain.dto.event.request;

import com.drinkhere.drinklystore.domain.entity.event.Event;
import com.drinkhere.drinklystore.domain.enums.EventCategory;

import java.time.LocalDateTime;
import java.util.List;

public record CreateEventRequest(
        String thumbnailPath,
        String title,
        String benefit,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String description,
        String redirectUrl,
        EventCategory eventCategory,
        List<String> eventImagePaths
) {
    public Event toEntity() {
        return Event.builder()
                .thumbnailPath(thumbnailPath)
                .title(title)
                .benefit(benefit)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .redirectUrl(redirectUrl)
                .eventCategory(eventCategory)
                .build();
    }
}
