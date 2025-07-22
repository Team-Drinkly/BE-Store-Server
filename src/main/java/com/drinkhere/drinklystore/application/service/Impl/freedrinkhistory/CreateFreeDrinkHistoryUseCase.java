package com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.common.exception.store.StoreException;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequest;
import com.drinkhere.drinklystore.domain.dto.request.CreateFreeDrinkHistoryRequestV2;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.service.freedrinkhistory.FreeDrinkHistoryCommandService;
import com.drinkhere.drinklystore.domain.service.store.StoreQueryService;
import com.drinkhere.drinklystore.openfeign.client.MemberClient;
import com.drinkhere.drinklystore.openfeign.dto.response.MemberResponse;
import com.drinkhere.drinklystore.redis.util.RedisUtil;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static com.drinkhere.drinklystore.common.exception.store.StoreErrorCode.ALREADY_USED_TODAY;
import static com.drinkhere.drinklystore.common.exception.store.StoreErrorCode.NOT_SUBSCIRBER;

@ApplicationService
@RequiredArgsConstructor
public class CreateFreeDrinkHistoryUseCase {
    private final FreeDrinkHistoryCommandService freeDrinkHistoryCommandService;
    private final StoreQueryService storeQueryService;
    private final MemberClient memberClient;
    private final RedisUtil redisUtil;
    private static final String REDIS_KEY_PREFIX = "free-drink:";


//    public void createFreeDrinkHistory(String isSubscribe, Long memberId, Long subscribeId, CreateFreeDrinkHistoryRequest createFreeDrinkHistoryRequest) {
//        if (isSubscribe.equals("false")) throw new StoreException(NOT_SUBSCIRBER);
//        Store store = storeQueryService.findById(createFreeDrinkHistoryRequest.storeId());
//
//        String redisKey = REDIS_KEY_PREFIX + memberId;
//        if (redisUtil.hasKey(redisKey)) {
//            throw new StoreException(ALREADY_USED_TODAY); // 오늘 이미 사용한 경우 예외 발생
//        }
//        // Feign Client 서버 간 통신으로 member nickname 들고옴.
//        MemberResponse memberResponse = memberClient.getMemberById(memberId);
//        String memberNickname = memberResponse.getNickname();
//
//        freeDrinkHistoryCommandService.createFreeDrinkHistory(memberId, memberNickname, subscribeId, store, createFreeDrinkHistoryRequest.providedDrink());
//
//        // 현재 시간
//        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
//
//        // 오늘 오후 12:00 (정오) 시간
//        LocalDateTime todayNoon = LocalDate.now().atTime(12, 0);
//
//        // 만료 시간 설정 (현재 시간이 오후 12:00 이후라면 내일 정오)
//        LocalDateTime expirationTime = now.isAfter(todayNoon)
//                ? todayNoon.plusDays(1)  // 내일 오후 12:00
//                : todayNoon;              // 오늘 오후 12:00
//
//        // TTL 계산
//        long ttl = Duration.between(now, expirationTime).getSeconds();
//
//        // Redis에 저장
//        redisUtil.saveAsValue(redisKey, "USED", ttl, TimeUnit.SECONDS);
//    }

    public void createFreeDrinkHistory(String isSubscribe, Long memberId, Long subscribeId, CreateFreeDrinkHistoryRequest request) {
        if (isSubscribe.equals("false")) throw new StoreException(NOT_SUBSCIRBER);

        Store store = storeQueryService.findById(request.storeId());

        LocalDate standardDate = getStandardDate();
        String dateStr = standardDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String redisKey = REDIS_KEY_PREFIX + dateStr + ":" + memberId + ":" + store.getId();

        if (redisUtil.hasKey(redisKey)) {
            throw new StoreException(ALREADY_USED_TODAY);
        }

        MemberResponse memberResponse = memberClient.getMemberById(memberId);
        String memberNickname = memberResponse.getNickname();

        freeDrinkHistoryCommandService.createFreeDrinkHistory(memberId, memberNickname, subscribeId, store, request.providedDrink());

        LocalDateTime expirationTime = standardDate.plusDays(1).atTime(12, 0);
        long ttl = Duration.between(LocalDateTime.now(ZoneId.of("Asia/Seoul")), expirationTime).getSeconds();

        redisUtil.saveAsValue(redisKey, "USED", ttl, TimeUnit.SECONDS);
    }

    public void createFreeDrinkHistoryV2(String isSubscribe, Long memberId, Long subscribeId, CreateFreeDrinkHistoryRequestV2 request) {
        if (isSubscribe.equals("false")) throw new StoreException(NOT_SUBSCIRBER);

        Store store = storeQueryService.findById(request.storeId());

        LocalDate standardDate = getStandardDate();
        String dateStr = standardDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String redisKey = REDIS_KEY_PREFIX + dateStr + ":" + memberId + ":" + store.getId();

        if (redisUtil.hasKey(redisKey)) {
            throw new StoreException(ALREADY_USED_TODAY);
        }

        MemberResponse memberResponse = memberClient.getMemberById(memberId);
        String memberNickname = memberResponse.getNickname();

        freeDrinkHistoryCommandService.createFreeDrinkHistoryV2(memberId, memberNickname, subscribeId, store, request.providedDrinkImageId(), request.providedDrink());

        LocalDateTime expirationTime = standardDate.plusDays(1).atTime(12, 0);
        long ttl = Duration.between(LocalDateTime.now(ZoneId.of("Asia/Seoul")), expirationTime).getSeconds();

        redisUtil.saveAsValue(redisKey, "USED", ttl, TimeUnit.SECONDS);
    }

    private LocalDate getStandardDate() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime todayNoon = LocalDate.now(ZoneId.of("Asia/Seoul")).atTime(12, 0);
        return now.isBefore(todayNoon) ? LocalDate.now(ZoneId.of("Asia/Seoul")).minusDays(1) : LocalDate.now(ZoneId.of("Asia/Seoul"));
    }
}
