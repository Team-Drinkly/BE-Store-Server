package com.drinkhere.drinklystore.util;

import com.drinkhere.drinklystore.domain.dto.OpeningHours;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // List<OpeningHours>를 JSON 문자열로 직렬화
    public static String serialization(List<OpeningHours> openingHours) {
        try {
            return objectMapper.writeValueAsString(openingHours);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("운영 시간 직렬화 실패", e);
        }
    }

    // JSON 문자열을 List<OpeningHours>로 역직렬화
    public static List<OpeningHours> deserialization(String json) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, OpeningHours.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("운영 시간 역직렬화 실패", e);
        }
    }
}
