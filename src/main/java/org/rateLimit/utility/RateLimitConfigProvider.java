package org.rateLimit.utility;

import org.rateLimit.dto.RateLimitConfig;
import org.rateLimit.enums.UserType;

import java.util.HashMap;
import java.util.Map;

public class RateLimitConfigProvider {

    Map<String, RateLimitConfig> rateLimitConfigs = new HashMap<>();

    {
        rateLimitConfigs.put("/search::FREE", RateLimitConfig.getTokenBucket(8D, 1D));
        rateLimitConfigs.put("/search::PAID", RateLimitConfig.getTokenBucket(12D, 8D));
        rateLimitConfigs.put("/login::FREE", RateLimitConfig.getSlidingWindow(8D, 1D));
        rateLimitConfigs.put("/login::PAID", RateLimitConfig.getSlidingWindow(12D, 8D));
    }

    public RateLimitConfig getConfigs(String url, UserType userType) {
        String key = getKey(url, userType);

        return rateLimitConfigs.getOrDefault(key, RateLimitConfig.getTokenBucket(5D,5D));
    }

    private String getKey(String url, UserType userType) {
        return url + "::" + userType;
    }


}
