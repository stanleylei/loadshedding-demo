package com.giganobs.loadsheddingdemo;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LoadSheddingService {

    private final AtomicInteger concurrentRequests = new AtomicInteger(0);
    private static final int MAX_CONCURRENT_REQUESTS = 15; // Threshold for load shedding

    public boolean requestStart() {
        System.out.println("Current load: " + concurrentRequests.get());
        if (concurrentRequests.get() >= MAX_CONCURRENT_REQUESTS) {
            System.out.println("Request rejected, max load reached.");
            return false; // Shed load if threshold exceeded
        }
        concurrentRequests.incrementAndGet();
        System.out.println("Request accepted, current load " + concurrentRequests.get());
        return true;
    }

    public void requestEnd() {
        concurrentRequests.decrementAndGet();
    }
}