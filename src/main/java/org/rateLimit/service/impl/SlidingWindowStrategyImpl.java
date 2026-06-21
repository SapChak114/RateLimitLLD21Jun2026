package org.rateLimit.service.impl;

import org.rateLimit.dto.RateLimitConfig;
import org.rateLimit.dto.RateLimitResponse;
import org.rateLimit.service.RateLimitStrategy;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowStrategyImpl implements RateLimitStrategy {

    Map<String, Queue<Double>> timestamps = new ConcurrentHashMap<>();

    @Override
    public RateLimitResponse isAllowed(String ip, String url, RateLimitConfig config) {
        String key = getKey(ip, url);

        Queue<Double> timestamp = timestamps.computeIfAbsent(key, obj -> new LinkedList<>());

        synchronized (timestamp) {
            Long now = System.currentTimeMillis();

            while (!timestamp.isEmpty() && (now - timestamp.peek()) > config.maxWindowsInMillis()) {
                timestamp.poll();
            }

            if (timestamp.size() < config.maxWindowSize()) {
                timestamp.add((double)now);
                return RateLimitResponse.allowed();
            } else {
                Double retryTime = config.maxWindowsInMillis() - (now - timestamp.peek());
                return  RateLimitResponse.denied(retryTime);
            }
        }
    }

    private String getKey(String ip, String url) {
        return ip + "::" + url;
    }


}
