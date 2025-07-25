package com.drinkhere.drinklystore.domain.dto.event.response;

import com.drinkhere.drinklystore.domain.entity.event.Event;
import com.drinkhere.drinklystore.domain.entity.event.EventImage;
import com.drinkhere.drinklystore.domain.enums.EventCategory;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import com.drinkhere.drinklystore.util.TimeUtil;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record CreateEventResponse(
        String thumbnailPath,
        String title,
        String benefit,
        String startDate,
        String endDate,
        String description,
        String redirectUrl,
        EventCategory eventCategory,
        List<String> eventImagePaths
) {
    public static CreateEventResponse toDto(Event event, List<EventImage> images, PresignedUrlService presignedUrlService) {
        List<String> eventImagePaths = images.stream()
                .map(img -> presignedUrlService.getPresignedUrlForGet(img.getEventImageUrl()))
                .collect(Collectors.toList());

        return CreateEventResponse.builder()
                .thumbnailPath(presignedUrlService.getPresignedUrlForGet(event.getThumbnailPath()))
                .title(event.getTitle())
                .benefit(event.getBenefit())
                .startDate(TimeUtil.refineToMonthDayWithDayOfWeek(event.getStartDate()))
                .endDate(TimeUtil.refineToMonthDayWithDayOfWeek(event.getEndDate()))
                .description(event.getDescription())
                .redirectUrl(event.getRedirectUrl())
                .eventCategory(event.getEventCategory())
                .eventImagePaths(eventImagePaths)
                .build();
    }

}
