package com.amalvadkar.ums.url.generator;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SlugGenerator {

    public String generate() {
        return UUID.randomUUID().toString();
    }
}
