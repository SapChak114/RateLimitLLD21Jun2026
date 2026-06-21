package org.rateLimit.utility;

import org.rateLimit.enums.StrategyType;
import org.rateLimit.service.RateLimitStrategy;
import org.rateLimit.service.impl.SlidingWindowStrategyImpl;
import org.rateLimit.service.impl.TokenBucketStrategyImpl;

public class RateLimitAlgorithmFactory {

    final TokenBucketStrategyImpl tokenBucket = new TokenBucketStrategyImpl();
    final SlidingWindowStrategyImpl slidingWindow = new SlidingWindowStrategyImpl();

    public RateLimitStrategy getRateLimitAlgo(StrategyType type) {
        return switch (type) {
            case TOKEN_BUCKET -> tokenBucket;
            case SLIDING_WINDOW -> slidingWindow;
        };
    }
}
