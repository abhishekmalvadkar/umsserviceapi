package com.amalvadkar.ums.common.model.dto;

public record LoggedInUser(Long userId, Long roleId, String device) {
    public static LoggedInUser from(Long userId, Long roleId, String device) {
        return new LoggedInUser(userId, roleId, device);
    }
}
