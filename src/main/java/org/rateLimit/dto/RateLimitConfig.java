package org.rateLimit.dto;

import org.rateLimit.enums.StrategyType;

public record RateLimitConfig(
        Double maxTokensInMillis,
        Double tokenRefillRateInMillis,
        Double maxWindowSize,
        Double maxWindowsInMillis,
        StrategyType strategyType
) {

    public static RateLimitConfig getTokenBucket(Double maxTokensInMillis, Double tokenRefillRateInMillis) {
        return new RateLimitConfig(maxTokensInMillis, tokenRefillRateInMillis, 0D, 0D, StrategyType.TOKEN_BUCKET);
    }

    public static RateLimitConfig getSlidingWindow(Double maxWindowSize, Double maxWindowsInMillis) {
        return new RateLimitConfig(0D, 0D, maxWindowSize, maxWindowsInMillis, StrategyType.SLIDING_WINDOW);
    }
}
