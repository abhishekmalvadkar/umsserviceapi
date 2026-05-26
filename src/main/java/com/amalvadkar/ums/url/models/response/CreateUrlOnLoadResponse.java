package com.amalvadkar.ums.url.models.response;

import com.amalvadkar.ums.common.model.response.HeaderResponse;

import java.util.List;

public record CreateUrlOnLoadResponse(List<HeaderResponse> headers) {
    public static CreateUrlOnLoadResponse from(List<HeaderResponse> headers) {
        return new CreateUrlOnLoadResponse(headers);
    }
}
