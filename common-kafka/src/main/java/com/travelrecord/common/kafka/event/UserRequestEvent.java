package com.travelrecord.common.kafka.event;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestEvent {
    private String requestId;
    private Long userId;
}
