package com.amalvadkar.ums.url.models.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Data
public class CreateUrlRequest {
    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "originalUrl is required")
    private String originalUrl;

    private String slug;

    public boolean hasSlug(){
        return isNotBlank(slug);
    }
}
