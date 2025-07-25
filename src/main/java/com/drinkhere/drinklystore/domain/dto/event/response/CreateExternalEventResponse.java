package com.drinkhere.drinklystore.domain.dto.event.response;

import com.drinkhere.drinklystore.domain.entity.event.ExternalEvent;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import com.drinkhere.drinklystore.util.TimeUtil;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateExternalEventResponse(
        String thumbnailPath,
        String title,
        String organizer,
        String description,
        String redirectUrl,
        String startDate,
        String endDate
) {
    public static CreateExternalEventResponse toDto(ExternalEvent externalEvent, PresignedUrlService presignedUrlService) {
        return CreateExternalEventResponse.builder()
                .thumbnailPath(presignedUrlService.getPresignedUrlForGet(externalEvent.getThumbnailPath()))
                .title(externalEvent.getTitle())
                .organizer(externalEvent.getOrganizer())
                .description(externalEvent.getDescription())
                .redirectUrl(externalEvent.getRedirectUrl())
                .startDate(TimeUtil.refineToDateTime(externalEvent.getStartDate()))
                .endDate(TimeUtil.refineToDateTime(externalEvent.getEndDate()))
                .build();
    }
}
