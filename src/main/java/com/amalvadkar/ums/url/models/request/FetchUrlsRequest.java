package com.amalvadkar.ums.url.models.request;

import lombok.Data;

import static java.util.Objects.nonNull;

@Data
public class FetchUrlsRequest {
    private Long urlStatusId;

    public boolean hasUrlStatusId() {
        return nonNull(urlStatusId);
    }
}
