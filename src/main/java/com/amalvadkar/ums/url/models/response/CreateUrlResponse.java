package com.amalvadkar.ums.url.models.response;

import lombok.Data;

@Data
public class CreateUrlResponse {
    private Long id;
    private String title;
    private String originalUrl;
    private String slug;
    private Long urlStatusId;
    private Long createdByUserId;
}
