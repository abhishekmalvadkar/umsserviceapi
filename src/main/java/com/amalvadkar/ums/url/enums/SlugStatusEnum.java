package com.amalvadkar.ums.url.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SlugStatusEnum {
    VALID_SLUG(""),
    UNKNOWN_SLUG("The requested short URL does not exist."),
    INACTIVE_SLUG("This short URL is currently inactive.");

    private final String message;

    public String message() {
        return message;
    }
}
