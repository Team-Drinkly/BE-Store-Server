package com.drinkhere.drinklystore.domain.dto.event.response;

import com.drinkhere.drinklystore.domain.entity.event.Event;
import com.drinkhere.drinklystore.domain.enums.EventCategory;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import com.drinkhere.drinklystore.util.TimeUtil;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record GetEventResponse(
        Long eventId,

        List<String> eventImagePaths,
        String title,

        String status,
        String dDay,

        String startDate,
        String endDate,
        String benefit,

        String description,

        EventCategory eventCategory,
        String redirectUrl
) {
    public static GetEventResponse toDto(Event event, PresignedUrlService presignedUrlService) {
        List<String> eventImagePaths = event.getEventImages().stream()
                .map(img -> presignedUrlService.getPresignedUrlForGet(img.getEventImageUrl()))
                .collect(Collectors.toList());

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime endDate = event.getEndDate();

        // status 계산
        String status = today.isAfter(endDate) ? "마감" : "진행중";

        // dDay 계산
        String dDay;
        long daysDiff = ChronoUnit.DAYS.between(today.toLocalDate(), endDate.toLocalDate());
        if (daysDiff == 0) dDay = "D-day";
        else if (daysDiff > 0) dDay = "D-" + daysDiff;
        else dDay = "D+" + Math.abs(daysDiff);

        return GetEventResponse.builder()
                .eventId(event.getId())
                .eventImagePaths(eventImagePaths)
                .title(event.getTitle())
                .status(status)
                .dDay(dDay)
                .startDate(TimeUtil.refineToMonthDayWithDayOfWeek(event.getStartDate()))
                .endDate(TimeUtil.refineToMonthDayWithDayOfWeek(endDate))
                .benefit(event.getBenefit())
                .description(event.getDescription())
                .eventCategory(event.getEventCategory())
                .redirectUrl(event.getRedirectUrl())
                .build();
    }
}