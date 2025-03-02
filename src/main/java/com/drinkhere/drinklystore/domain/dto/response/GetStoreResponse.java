package com.drinkhere.drinklystore.domain.dto.response;

import com.drinkhere.drinklystore.domain.dto.OpeningHours;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.enums.StoreImageType;
import com.drinkhere.drinklystore.util.JsonUtil;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public record GetStoreResponse(
        Long storeId,
        Long ownerId,
        String storeName,
        String storeMainImageUrl,
        String storeDescription,
        String isOpen,
        String openingInfo,
        List<OpeningHours> openingHours,
        String storeTel,
        String storeAddress,
        String storeDetailAddress,
        String instagramUrl,
        String availableDays,
        String latitude,
        String longitude,
        List<ImageInfoResponse> availableDrinkImageUrls,
        List<ImageInfoResponse> menuImageUrls
) {
    public static GetStoreResponse toDto(Store store, PresignedUrlService presignedUrlService) {
        String presignedUrl = presignedUrlService.getPresignedUrlForGet(store.getStoreMainImageUrl());
        
        List<ImageInfoResponse> availableDrinkImages = store.getStoreImages().stream()
                .filter(image -> image.getStoreImageType() == StoreImageType.AVAILABLE_DRINK)
                .map(image -> ImageInfoResponse.toDto(image.getId(), presignedUrlService.getPresignedUrlForGet(image.getStoreImageUrl()), image.getStoreImageDescription()))
                .collect(Collectors.toList());

        List<ImageInfoResponse> menuImages = store.getStoreImages().stream()
                .filter(image -> image.getStoreImageType() == StoreImageType.MENU)
                .map(image -> ImageInfoResponse.toDto(image.getId(), presignedUrlService.getPresignedUrlForGet(image.getStoreImageUrl()), image.getStoreImageDescription()))
                .collect(Collectors.toList());

        String openingHours = store.getOpeningHours();

        String isOpen = "영업종료";
        String openingInfo = "";
        List<OpeningHours> openingHoursList;

        // openingHoursList가 null인 경우
        if (openingHours == null) {
            openingHoursList = null;
            isOpen = "가게 정보 미등록";
            openingInfo = "가게 정보를 등록해주세요";

        }
        else {
            // OpeningHours 리스트 역직렬화
            openingHoursList = JsonUtil.deserialization(openingHours);

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

        return new GetStoreResponse(
                store.getId(),
                store.getOwnerId(),
                store.getStoreName(),
                presignedUrl,
                store.getStoreDescription(),
                isOpen,
                openingInfo,
                openingHoursList,
                store.getStoreTel(),
                store.getStoreAddress(),
                store.getStoreDetailAddress(),
                store.getInstagramUrl(),
                store.getAvailableDays(),
                store.getLatitude(),
                store.getLongitude(),
                availableDrinkImages,
                menuImages
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
