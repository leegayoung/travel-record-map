package com.travelrecord.auth.adapter.kafka;

import com.travelrecord.auth.dto.AuthDto;
import com.travelrecord.auth.security.UserLookupPort;
import com.travelrecord.common.kafka.event.UserLookupRequestEvent;
import com.travelrecord.common.kafka.event.UserLookupResponseEvent;
import com.travelrecord.common.kafka.producer.KafkaEventPublisher;
import com.travelrecord.common.kafka.topic.KafkaTopics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class UserLookupKafkaAdapter implements UserLookupPort {
    private final KafkaEventPublisher kafkaEventPublisher;
    private final UserLookupPendingStore pendingRequestStore;

    @Override
    public void requestSignup(AuthDto.SignupRequest request) {

    }

    @Override
    public UserDetailResponse findUserDetailByEmail(String email) {
        return null;
    }
}
