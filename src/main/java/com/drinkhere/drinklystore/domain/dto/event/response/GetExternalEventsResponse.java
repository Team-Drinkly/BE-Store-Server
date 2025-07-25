package com.drinkhere.drinklystore.domain.dto.event.response;

import com.drinkhere.drinklystore.domain.entity.event.Event;
import com.drinkhere.drinklystore.domain.entity.event.ExternalEvent;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import com.drinkhere.drinklystore.util.TimeUtil;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record GetExternalEventsResponse(
        Long externalEventId,

        String thumbnailPath,
        String title,

        String organizer,
        String description,
        String redirectUrl,

        String status
) {
    public static GetExternalEventsResponse toDto(ExternalEvent event, PresignedUrlService presignedUrlService) {
        LocalDateTime now = LocalDateTime.now();
        String status = (now.isAfter(event.getEndDate())) ? "마감"
                : (now.isBefore(event.getStartDate())) ? "진행예정"
                : "진행중";

        return GetExternalEventsResponse.builder()
                .externalEventId(event.getId())
                .thumbnailPath(presignedUrlService.getPresignedUrlForGet(event.getThumbnailPath()))
                .title(event.getTitle())
                .organizer(event.getOrganizer())
                .description(event.getDescription())
                .redirectUrl(event.getRedirectUrl())
                .status(status)
                .build();
    }
}