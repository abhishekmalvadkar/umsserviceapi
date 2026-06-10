package com.amalvadkar.ums.url.models.request;

import com.amalvadkar.ums.common.model.dto.HeaderConfigAware;
import jakarta.validation.constraints.NotNull;

public record UpdateUrlRequest(
        @NotNull(message = "headerConfigId is required")
        Long headerConfigId,

        String value,

        @NotNull(message = "recordId is required")
        Long recordId) implements HeaderConfigAware {
}
