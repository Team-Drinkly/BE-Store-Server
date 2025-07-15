package com.drinkhere.drinklystore.events.signup.listener;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Getter
@Setter
@RefreshScope
@ConfigurationProperties(prefix = "slack.webhook")
public class SlackWebhookService {

    private String ownerUrl; // application.yml에서 주입됨

    private final Slack slack = Slack.getInstance();

    /**
     * Slack 알림을 보냅니다.
     *
     * @param type 메시지 수신 대상 구분 (예: owner)
     * @param message 보낼 텍스트 메시지
     * @return WebhookResponse 슬랙 응답 객체
     */
    public WebhookResponse alert(String type, String message) {
        try {
            Payload payload = Payload.builder().text(message).build();
            if ("owner".equalsIgnoreCase(type)) {
                return slack.send(ownerUrl, payload);
            }
            throw new IllegalArgumentException("지원하지 않는 Slack Webhook type: " + type);
        } catch (IOException e) {
            throw new RuntimeException("Slack Webhook 전송 실패", e);
        }
    }
}
