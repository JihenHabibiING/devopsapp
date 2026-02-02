package com.jihen.devopsapp.service;

import com.jihen.devopsapp.util.Clock;

public class GreetingService {
    private final Clock clock;

    public GreetingService(Clock clock) {
        this.clock = clock;
    }

    public String greet() {
        int h = clock.hour();
        if (h < 12) return "Good morning";
        if (h < 18) return "Good afternoon";
        return "Good evening";
    }
}
