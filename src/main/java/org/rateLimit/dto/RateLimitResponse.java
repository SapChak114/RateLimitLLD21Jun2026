package org.rateLimit.dto;

public record RateLimitResponse(Boolean isAllowed,
        Double retryTime,
        String rateLimitMsg) {

    public static RateLimitResponse allowed() {
        return new RateLimitResponse(true, 0D, "Passed");
    }

    public static RateLimitResponse denied(Double retryTime) {
        return new RateLimitResponse(false, retryTime, "Failed");
    }

    @Override
    public String toString() {
        return "{" +
                "isAllowed=" + isAllowed +
                ", retryTime=" + retryTime +
                ", rateLimitMsg='" + rateLimitMsg + '\'' +
                '}';
    }
}
