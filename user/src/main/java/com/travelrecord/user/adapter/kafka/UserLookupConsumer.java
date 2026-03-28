package com.travelrecord.user.adapter.kafka;

import com.travelrecord.common.kafka.event.UserLookupRequestEvent;
import com.travelrecord.common.kafka.event.UserLookupResponseEvent;
import com.travelrecord.common.kafka.producer.KafkaEventPublisher;
import com.travelrecord.common.kafka.topic.KafkaTopics;
import com.travelrecord.user.domain.User;
import com.travelrecord.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLookupConsumer {
    private final UserService userService;
    private final KafkaEventPublisher kafkaEventPublisher;

    @KafkaListener(topics = KafkaTopics.USER_LOOKUP_REQUEST, groupId = "user-service")
    public void handleLookup(UserLookupRequestEvent event) {
        try {
            User user = userService.findById(event.getUserId());
            kafkaEventPublisher.publish(KafkaTopics.USER_LOOKUP_RESPONSE, new UserLookupResponseEvent(
                    event.getRequestId(),
                    user.getId(),
                    user.getEmail(), // assuming email maps to name
                    user.getEmail()
            ));
        } catch (Exception e) {
            // Log error
        }
    }
}
