package com.drinkhere.drinklystore.events.signup.listener;

import com.drinkhere.drinklystore.events.signup.event.StoreRegisterEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreRegisterListener implements ApplicationListener<StoreRegisterEvent> {

    private final SlackWebhookService slackWebhookService;

    @Async
    @Override
    public void onApplicationEvent(StoreRegisterEvent event) {
        sendSlackNotification(event.getType(), event.getOrder(), event.getName(), event.getMobileNo());
    }

    private void sendSlackNotification(String type, Long id, String name, String mobileNo) {
        String message = String.format(
                "🎉 드링클리 %d번째 신규 매장이 등록됐어요!\n🏪 매장명 : %s\n📞 매장 연락처 : %s",
                id, name, mobileNo
        );
        slackWebhookService.alert(type, message);
    }
}
