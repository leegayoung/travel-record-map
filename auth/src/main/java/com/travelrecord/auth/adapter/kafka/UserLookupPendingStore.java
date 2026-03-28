package com.travelrecord.auth.adapter.kafka;

import com.travelrecord.common.kafka.event.UserLookupResponseEvent;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserLookupPendingStore {
    private final Map<String, CompletableFuture<UserLookupResponseEvent>> pendingRequests = new ConcurrentHashMap<>();

    public void put(String requestId, CompletableFuture<UserLookupResponseEvent> future) {
        pendingRequests.put(requestId, future);
    }

    public CompletableFuture<UserLookupResponseEvent> remove(String requestId) {
        return pendingRequests.remove(requestId);
    }
}
