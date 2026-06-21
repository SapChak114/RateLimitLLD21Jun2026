package org.rateLimit.service.impl;

import org.rateLimit.dto.Bucket;
import org.rateLimit.dto.RateLimitConfig;
import org.rateLimit.dto.RateLimitResponse;
import org.rateLimit.service.RateLimitStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketStrategyImpl implements RateLimitStrategy {

    Map<String, Bucket> buckets = new ConcurrentHashMap<>();


    @Override
    public RateLimitResponse isAllowed(String ip, String url, RateLimitConfig config) {
        String key = getKey(ip, url);
        Bucket bucket = buckets.computeIfAbsent(key, k -> new Bucket(config.maxTokensInMillis(), (double)System.currentTimeMillis()));

        synchronized (bucket) {
            refillTokens(bucket, config);

            if (bucket.getTokens() >= 1) {
                return RateLimitResponse.allowed();
            } else {
                Double remainToken = 1 - bucket.getTokens();
                Double retryTime = remainToken / config.tokenRefillRateInMillis();

                return RateLimitResponse.denied(retryTime);
            }
        }

    }

    private void refillTokens(Bucket bucket, RateLimitConfig config) {
        Long now = System.currentTimeMillis();
        Double elapsed = now - bucket.getLastUpdatedTime();

        Double refill = elapsed * config.tokenRefillRateInMillis();
        Double tokens = Math.min(config.maxTokensInMillis(), bucket.getTokens() + refill);

        bucket.setTokens(tokens);
        bucket.setLastUpdatedTime((double)now);
    }

    private String getKey(String ip, String url) {
        return ip + "::" + url;
    }


}
