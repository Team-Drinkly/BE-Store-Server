package com.drinkhere.drinklystore.infras3.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.drinkhere.drinklystore.infras3.config.S3Config;
import com.drinkhere.drinklystore.infras3.dto.request.GetPresignedUrlListRequest;
import com.drinkhere.drinklystore.infras3.dto.request.GetPresignedUrlRequest;
import com.drinkhere.drinklystore.infras3.dto.response.GetPresignedUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@RefreshScope
public class PresignedUrlService {

    @Value("${cloud.s3.bucket}") private String bucket;
    @Value("${cloud.s3.expTime}") private Long expTime;
    private final S3Config s3Config;


    /**
     * PUT용 presigned url 발급 -> 이미지 업로드
     */

    public GetPresignedUrlResponse getPresignedUrlForPut(GetPresignedUrlRequest getPresignedUrlRequest) {
        String filePath = createPath(getPresignedUrlRequest.prefix(), getPresignedUrlRequest.fileName());
        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePresignedUrlRequest(bucket, filePath, HttpMethod.PUT);
        URL url = s3Config.amazonS3().generatePresignedUrl(generatePresignedUrlRequest);
        return GetPresignedUrlResponse.of(url.toString(), filePath);
    }

    public GetPresignedUrlResponse getPresignedUrlForPutWithNoIdentifier(GetPresignedUrlRequest getPresignedUrlRequest) {
        String filePath = createPathNoIdentifier(getPresignedUrlRequest.prefix(), getPresignedUrlRequest.fileName());
        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePresignedUrlRequest(bucket, filePath, HttpMethod.PUT);
        URL url = s3Config.amazonS3().generatePresignedUrl(generatePresignedUrlRequest);
        return GetPresignedUrlResponse.of(url.toString(), filePath);
    }

    public List<GetPresignedUrlResponse> getPresignedUrlsForPut(GetPresignedUrlListRequest getPresignedUrlRequests) {
        return getPresignedUrlRequests.requests().stream()
                .map(this::getPresignedUrlForPut) // getPresignedUrlForPut 메서드 참조
                .toList();
    }

    /**
     * GET용 presigned url 발급 -> 이미지 조회
     */
    public String getPresignedUrlForGet(String filePath) {
        if (filePath != null) {
            GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePresignedUrlRequest(bucket, filePath, HttpMethod.GET);
            URL url = s3Config.amazonS3().generatePresignedUrl(generatePresignedUrlRequest);
            return url.toString();
        }
        return filePath;
    }

    /**
     * Amazon S3 SDK에서 Presigned URL을 생성하기 위한 요청 객체를 생성
     */
    private GeneratePresignedUrlRequest getGeneratePresignedUrlRequest(String bucket, String fileName, HttpMethod method) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, fileName)
                .withMethod(method)
                .withExpiration(getPresignedUrlExpiration());

        return generatePresignedUrlRequest;
    }

    //
    private Date getPresignedUrlExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += expTime;
        expiration.setTime(expTimeMillis);

        return expiration;
    }

    // 파일 경로 생성 -> {prefix}/{yyyyMMddHHmmss}-{UUID}-{파일명}.png
    private String createPath(String prefix, String fileName) {
        String fileUniqueId = UUID.randomUUID().toString();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return String.format("%s/%s-%s-%s", prefix, timestamp, fileUniqueId, fileName);
    }

    private String createPathNoIdentifier(String prefix, String fileName) {
        return String.format("%s/%s", prefix, fileName);
    }
}
