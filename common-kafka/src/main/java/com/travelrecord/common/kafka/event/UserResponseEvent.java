package com.travelrecord.common.kafka.event;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseEvent {
    private String requestId;
    private Long userId;
    private String name;
    private String email;
}
