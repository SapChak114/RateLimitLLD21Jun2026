package org.rateLimit.service;

import org.rateLimit.dto.RateLimitConfig;
import org.rateLimit.dto.RateLimitResponse;

public interface RateLimitStrategy {

    public RateLimitResponse isAllowed(String ip, String url, RateLimitConfig rateLimitConfig);
}
