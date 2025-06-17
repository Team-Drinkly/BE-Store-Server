package com.drinkhere.drinklystore.domain.dto.response;

import com.drinkhere.drinklystore.domain.dto.OpeningHours;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.entity.StoreImage;
import com.drinkhere.drinklystore.domain.enums.StoreImageType;
import com.drinkhere.drinklystore.util.DistanceUtil;
import com.drinkhere.drinklystore.util.JsonUtil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public record GetStoresByLocationResponse(
        Long id,
        String storeName,
        String storeMainImageUrl,
        String latitude,
        String longitude,
        String isOpen,
        boolean isAvailable,
        String openingInfo,
        String storeTel,
        String storeAddress,
        List<String> availableDrinks,
        double distance
) {
    public static GetStoresByLocationResponse toDto(Store store, PresignedUrlService presignedUrlService, double lat, double lon) {

        String presignedUrl = presignedUrlService.getPresignedUrlForGet(store.getStoreMainImageUrl());

        List<String> availableDrinks = store.getStoreImages().stream()
                .filter(image -> image.getStoreImageType() == StoreImageType.AVAILABLE_DRINK)
                .map(StoreImage::getStoreImageDescription)
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
                // 오늘이 휴일인지 확인
                if (!todayOpeningHours.isOpen()) {
                    isOpen = "오늘 휴무";
                    openingInfo = "매주 " + getKoreanDay(DayOfWeek.valueOf(todayOpeningHours.day())) + " 휴무";
                } else {
                    // 현재 시간 가져오기
                    LocalTime currentTime = LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime openTime = LocalTime.parse(todayOpeningHours.openTime(), formatter);
                    LocalTime closeTime = LocalTime.parse(todayOpeningHours.closeTime(), formatter);

                    // 만약 closeTime이 "00:00 이후"이면 -> 다음 날 새벽까지 영업하는 것으로 간주
                    boolean closesNextDay = closeTime.isBefore(openTime);  // 예: 18:00 ~ 01:00

                    // 영업 중인지 판별
                    if ((currentTime.isAfter(openTime) && currentTime.isBefore(closeTime)) ||
                            (closesNextDay && (currentTime.isAfter(openTime) || currentTime.isBefore(closeTime)))) {
                        isOpen = "영업중";
                        openingInfo = closeTime + " 영업종료";
                    } else {
                        isOpen = "영업종료";

                        // 다음 영업일 찾기
                        int nextDayIndex = (todayIndex + 1) % 7;
                        OpeningHours nextDayOpeningHours = openingHoursList.get(nextDayIndex);

                        // 다음 영업일이 휴무가 아닐 경우, 영업 시작 시간을 표시
                        if (nextDayOpeningHours.isOpen()) {
                            openingInfo = nextDayOpeningHours.openTime() + " 영업시작";
                        } else {
                            openingInfo = "내일 휴무";
                        }
                    }
                }
            }
        }


        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        String koreanDay = switch (dayOfWeek) {
            case MONDAY -> "월";
            case TUESDAY -> "화";
            case WEDNESDAY -> "수";
            case THURSDAY -> "목";
            case FRIDAY -> "금";
            case SATURDAY -> "토";
            case SUNDAY -> "일";
        };

        boolean isAvailable = false;
        StringTokenizer st = new StringTokenizer(store.getAvailableDays(), " ");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.equals(koreanDay) && isOpen.equals("영업중")) {
                isAvailable = true;
                break;
            }
        }

        return new GetStoresByLocationResponse(
                store.getId(),
                store.getStoreName(),
                presignedUrl,
                store.getLatitude(),
                store.getLongitude(),
                isOpen,
                isAvailable,
                openingInfo,
                store.getStoreTel(),
                store.getStoreAddress(),
                availableDrinks,
                DistanceUtil.calculateDistance(lat, lon, Double.parseDouble(store.getLatitude()), Double.parseDouble(store.getLongitude()))
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
