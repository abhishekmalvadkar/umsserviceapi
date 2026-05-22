package com.amalvadkar.ums.auth.model.response;

import lombok.Data;

import java.util.Map;

@Data
public class SignInResponse {
    private Map<String, Object> metadata;
}
