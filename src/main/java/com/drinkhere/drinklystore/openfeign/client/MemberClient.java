package com.drinkhere.drinklystore.openfeign.client;

import com.drinkhere.drinklystore.openfeign.dto.response.MemberResponse;
import com.drinkhere.drinklystore.openfeign.dto.response.OwnerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "memberClient", url = "${member-service.url}")
public interface MemberClient {
    @GetMapping("/profile/client/{memberId}")
    MemberResponse getMemberById(@PathVariable("memberId") Long memberId);
}
