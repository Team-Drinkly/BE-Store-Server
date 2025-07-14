package com.drinkhere.drinklystore.application.presentation;

import com.drinkhere.drinklystore.common.response.ApplicationResponse;
import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import com.drinkhere.drinklystore.infras3.dto.request.GetPresignedUrlListRequest;
import com.drinkhere.drinklystore.infras3.dto.request.GetPresignedUrlRequest;
import com.drinkhere.drinklystore.infras3.dto.response.GetPresignedUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/store/o/presigned-url")
@RequiredArgsConstructor
public class PresignedUrlController {
    private final PresignedUrlService presignedUrlService;

    @PostMapping
    public ApplicationResponse<GetPresignedUrlResponse> getPresignedUrl(@RequestBody GetPresignedUrlRequest getPresignedUrlRequest) {
        return ApplicationResponse.created(presignedUrlService.getPresignedUrlForPut(getPresignedUrlRequest));
    }

    @PostMapping("/no-identifier")
    public ApplicationResponse<GetPresignedUrlResponse> getPresignedUrlWithNoIdentifier(@RequestBody GetPresignedUrlRequest getPresignedUrlRequest) {
        return ApplicationResponse.created(presignedUrlService.getPresignedUrlForPutWithNoIdentifier(getPresignedUrlRequest));
    }

    @PostMapping("/batch")
    public ApplicationResponse<List<GetPresignedUrlResponse>> getPresignedUrls(@RequestBody GetPresignedUrlListRequest getPresignedUrlListRequest) {
        return ApplicationResponse.created(presignedUrlService.getPresignedUrlsForPut(getPresignedUrlListRequest));
    }
}
