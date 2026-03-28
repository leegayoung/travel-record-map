package com.travelrecord.auth.adapter.kafka;

import com.travelrecord.common.kafka.event.UserLookupResponseEvent;
import com.travelrecord.common.kafka.topic.KafkaTopics;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class UserLookupResponseConsumer {
    private final UserLookupPendingStore pendingRequestStore;

    @KafkaListener(topics = KafkaTopics.USER_LOOKUP_RESPONSE, groupId = "auth-service")
    public void handleResponse(UserLookupResponseEvent event) {
        CompletableFuture<UserLookupResponseEvent> future = pendingRequestStore.remove(event.getRequestId());
        if (future != null) {
            future.complete(event);
        }
    }
}
