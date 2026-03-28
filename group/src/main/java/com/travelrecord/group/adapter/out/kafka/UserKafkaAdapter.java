package com.travelrecord.group.adapter.out.kafka;

import com.travelrecord.common.kafka.event.UserRequestEvent;
import com.travelrecord.common.kafka.event.UserResponseEvent;
import com.travelrecord.common.kafka.producer.KafkaEventPublisher;
import com.travelrecord.common.kafka.topic.KafkaTopics;
import com.travelrecord.group.application.port.out.UserPort;
import com.travelrecord.group.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class UserKafkaAdapter implements UserPort {
    private final KafkaEventPublisher kafkaEventPublisher;
    private final Map<String, CompletableFuture<UserResponseEvent>> pendingRequests = new ConcurrentHashMap<>();

    @Override
    public UserDto getUser(Long userId) {
        String requestId = UUID.randomUUID().toString();
        CompletableFuture<UserResponseEvent> future = new CompletableFuture<>();
        pendingRequests.put(requestId, future);

        kafkaEventPublisher.publish(KafkaTopics.USER_REQUEST, new UserRequestEvent(requestId, userId));

        try {
            UserResponseEvent response = future.get(5, TimeUnit.SECONDS);
            return new UserDto(response.getUserId(), response.getName(), response.getEmail());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get user info via Kafka", e);
        } finally {
            pendingRequests.remove(requestId);
        }
    }

    @KafkaListener(topics = KafkaTopics.USER_RESPONSE, groupId = "group-service")
    public void handleResponse(UserResponseEvent event) {
        CompletableFuture<UserResponseEvent> future = pendingRequests.remove(event.getRequestId());
        if (future != null) {
            future.complete(event);
        }
    }
}
