package com.drinkhere.drinklystore.domain.dto.event.response;

import com.drinkhere.drinklystore.domain.entity.event.Event;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record GetEventsResponse(
        Long eventId,
        String thumbnailPath,
        String title,
        String status,     // 진행 중 / 마감
        String dDay        // D-3 / D-day / D+2
) {
    public static GetEventsResponse toDto(Event event, PresignedUrlService presignedUrlService) {
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

        return new GetEventsResponse(
                event.getId(),
                presignedUrlService.getPresignedUrlForGet(event.getThumbnailPath()),
                event.getTitle(),
                status,
                dDay
        );
    }
}