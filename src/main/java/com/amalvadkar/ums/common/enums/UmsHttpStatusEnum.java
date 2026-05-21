package com.amalvadkar.ums.common.enums;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
public enum UmsHttpStatusEnum {
    SUCCESS(OK.value());

    private final int code;

    public int code() {
        return code;
    }
}
