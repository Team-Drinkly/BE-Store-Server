package com.drinkhere.drinklystore.openfeign.dto.response;

public record MemberResponse(
        Payload payload
) {
    public String getNickname() {
        return payload.nickname();
    }

    public record Payload(String nickname) {}
}
