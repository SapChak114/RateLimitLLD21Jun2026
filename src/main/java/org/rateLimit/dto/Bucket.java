package org.rateLimit.dto;

public class Bucket {

    Double tokens;
    Double lastUpdatedTime;

    public Bucket(Double tokens, Double lastUpdatedTime) {
        this.tokens = tokens;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Double getTokens() {
        return tokens;
    }

    public void setTokens(Double tokens) {
        this.tokens = tokens;
    }

    public Double getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Double lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
