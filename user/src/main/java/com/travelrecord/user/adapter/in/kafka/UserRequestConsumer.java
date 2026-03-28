package com.travelrecord.user.adapter.in.kafka;

import com.travelrecord.common.kafka.event.UserRequestEvent;
import com.travelrecord.common.kafka.event.UserResponseEvent;
import com.travelrecord.common.kafka.producer.KafkaEventPublisher;
import com.travelrecord.common.kafka.topic.KafkaTopics;
import com.travelrecord.user.domain.User;
import com.travelrecord.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRequestConsumer {
    private final UserService userService;
    private final KafkaEventPublisher kafkaEventPublisher;

    @KafkaListener(topics = KafkaTopics.USER_REQUEST, groupId = "user-service")
    public void handleRequest(UserRequestEvent event) {
        try {
            User user = userService.findById(event.getUserId());
            kafkaEventPublisher.publish(KafkaTopics.USER_RESPONSE, new UserResponseEvent(
                    event.getRequestId(),
                    user.getId(),
                    user.getEmail(),
                    user.getEmail()
            ));
        } catch (Exception e) {
            // Error handling
        }
    }
}
