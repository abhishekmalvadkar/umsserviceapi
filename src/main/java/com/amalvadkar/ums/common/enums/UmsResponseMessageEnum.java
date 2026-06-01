package com.amalvadkar.ums.common.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UmsResponseMessageEnum {
    SIGNED_IN_SUCCESSFULLY("Signed in successfully"),
    FETCHED_SUCCESSFULLY("Fetched successfully"),
    CHECKED_SUCCESSFULLY("Checked successfully"),
    CREATED_SUCCESSFULLY("Created successfully");

    private final String value;

    public String value() {
        return value;
    }
}
