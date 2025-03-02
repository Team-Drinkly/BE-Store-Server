package com.drinkhere.drinklystore.domain.dto.response;

import com.drinkhere.drinklystore.domain.dto.OpeningHours;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.entity.StoreImage;
import com.drinkhere.drinklystore.domain.enums.StoreImageType;
import com.drinkhere.drinklystore.util.JsonUtil;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public record GetStoresByLocationResponse(
        Long id,
        String storeName,
        String storeMainImageUrl,
        String latitude,
        String longitude,
        String isOpen,
        String openingInfo,
        String storeTel,
        String storeAddress,
        List<String> availableDrinks
) {
    public static GetStoresByLocationResponse toDto(Store store, PresignedUrlService presignedUrlService) {
        String presignedUrl = presignedUrlService.getPresignedUrlForGet(store.getStoreMainImageUrl());

        List<String> availableDrinks = store.getStoreImages().stream()
                .filter(image -> image.getStoreImageType() == StoreImageType.AVAILABLE_DRINK)
                .map(StoreImage::getStoreImageDescription)
                .collect(Collectors.toList());

        String openingHours = store.getOpeningHours();

        String isOpen = "영업종료";
        String openingInfo = "";

        // openingHoursList가 null인 경우
        if (openingHours == null) {
            isOpen = "가게 정보 미등록";
            openingInfo = "가게 정보를 등록해주세요";
        } else {
            // OpeningHours 리스트 역직렬화
            List<OpeningHours> openingHoursList = JsonUtil.deserialization(openingHours);

            // 요일 인덱스를 0 (월요일) 부터 6 (일요일)로 설정
            int todayIndex = java.time.LocalDate.now().getDayOfWeek().getValue() - 1; // MONDAY = 1, SUNDAY = 7, -> MONDAY = 0, SUNDAY = 6

            // 오늘의 요일에 해당하는 OpeningHours 찾기
            OpeningHours todayOpeningHours = openingHoursList.get(todayIndex);

            if (todayOpeningHours != null) {
                // 오늘이 휴일인 경우
                if (!todayOpeningHours.isOpen()) {
                    isOpen = "휴무일";
                    openingInfo = "매주 " + getKoreanDay(DayOfWeek.valueOf(todayOpeningHours.day())) + " 휴무";
                } else {
                    // 영업시간에 현재시간이 포함되지 않으면 영업 종료 처리
                    LocalTime currentTime = LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime openTime = LocalTime.parse(todayOpeningHours.openTime(), formatter);
                    LocalTime closeTime = LocalTime.parse(todayOpeningHours.closeTime(), formatter);

                    // 영업시간에 현재시간이 포함되지 않으면
                    if (currentTime.isBefore(openTime) || currentTime.isAfter(closeTime)) {
                        isOpen = "영업종료";
                        // 다음날 영업 시작 시간 찾기 (현재 요일 인덱스 + 1)
                        int nextDayIndex = (todayIndex + 1) % 7;  // 다음날의 인덱스 (0-6 범위로 순환)
                        OpeningHours nextDayOpeningHours = openingHoursList.get(nextDayIndex);
                        openingInfo = nextDayOpeningHours.openTime() + "에 영업 시작"; // 다음날의 영업 시작 시간
                    } else {
                        // 영업 중인 경우
                        isOpen = "영업중";
                        openingInfo = closeTime.format(formatter) + "에 영업 종료";
                    }
                }
            }
        }

        return new GetStoresByLocationResponse(
                store.getId(),
                store.getStoreName(),
                presignedUrl,
                store.getLatitude(),
                store.getLongitude(),
                isOpen,
                openingInfo,
                store.getStoreTel(),
                store.getStoreAddress(),
                availableDrinks
        );
    }

    public static String getKoreanDay(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                return "월요일";
            case TUESDAY:
                return "화요일";
            case WEDNESDAY:
                return "수요일";
            case THURSDAY:
                return "목요일";
            case FRIDAY:
                return "금요일";
            case SATURDAY:
                return "토요일";
            case SUNDAY:
                return "일요일";
            default:
                return "알 수 없음";
        }
    }
}
