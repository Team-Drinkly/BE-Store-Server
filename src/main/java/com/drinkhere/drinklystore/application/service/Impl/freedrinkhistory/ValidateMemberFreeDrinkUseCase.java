package com.drinkhere.drinklystore.application.service.Impl.freedrinkhistory;

import com.drinkhere.drinklystore.common.annotation.ApplicationService;
import com.drinkhere.drinklystore.redis.util.RedisUtil;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@ApplicationService
@RequiredArgsConstructor
public class ValidateMemberFreeDrinkUseCase {
    private final RedisUtil redisUtil;
    private static final String REDIS_KEY_PREFIX = "free-drink:";

    public Boolean validateMemberFreeDrinkUseCase(Long memberId, Long storeId) {
        LocalDate standardDate = getStandardDate();
        String dateStr = standardDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String redisKey = REDIS_KEY_PREFIX + dateStr + ":" + memberId + ":" + storeId;

        return redisUtil.hasKey(redisKey);
    }

    private LocalDate getStandardDate() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime todayNoon = LocalDate.now(ZoneId.of("Asia/Seoul")).atTime(12, 0);
        return now.isBefore(todayNoon) ? LocalDate.now(ZoneId.of("Asia/Seoul")).minusDays(1) : LocalDate.now(ZoneId.of("Asia/Seoul"));
    }
}
