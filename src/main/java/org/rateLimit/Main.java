package org.rateLimit;

import org.rateLimit.enums.UserType;
import org.rateLimit.service.RateLimitService;

public class Main {
    public static void main(String[] args) {


        RateLimitService rateLimitService = new RateLimitService();

        for (int i = 0; i<15; i++) {
            System.out.println(rateLimitService.checkLimit("192.168.1.1", "/search", UserType.FREE));
        }

        System.out.println("SlidingWindow");
        for (int i = 0; i<15; i++) {
            System.out.println(rateLimitService.checkLimit("192.168.1.1", "/login", UserType.FREE));
        }
    }
}