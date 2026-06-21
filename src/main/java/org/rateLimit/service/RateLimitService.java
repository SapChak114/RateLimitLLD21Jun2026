package org.rateLimit.service;

import org.rateLimit.dto.RateLimitConfig;
import org.rateLimit.dto.RateLimitResponse;
import org.rateLimit.enums.UserType;
import org.rateLimit.utility.RateLimitAlgorithmFactory;
import org.rateLimit.utility.RateLimitConfigProvider;

public class RateLimitService {
    RateLimitAlgorithmFactory rateLimitAlgorithmFactory = new RateLimitAlgorithmFactory();
    RateLimitConfigProvider rateLimitConfigProvider = new RateLimitConfigProvider();


    public RateLimitResponse checkLimit(String ip, String url, UserType userType) {
        RateLimitConfig config = rateLimitConfigProvider.getConfigs(url, userType);
        RateLimitStrategy rateLimitStrategy = rateLimitAlgorithmFactory.getRateLimitAlgo(config.strategyType());

        return rateLimitStrategy.isAllowed(ip, url, config);
    }
}
