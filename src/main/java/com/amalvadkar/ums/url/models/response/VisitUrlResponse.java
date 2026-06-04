package com.amalvadkar.ums.url.models.response;

import com.amalvadkar.ums.url.entities.UrlEntity;
import com.amalvadkar.ums.url.enums.SlugStatusEnum;

import static com.amalvadkar.ums.url.enums.SlugStatusEnum.*;

public record VisitUrlResponse(String originalUrl, SlugStatusEnum slugStatus) {

    public static VisitUrlResponse withValidSlugStatus(UrlEntity entity){
        return new VisitUrlResponse(entity.getOriginalUrl(), VALID_SLUG);
    }

    public static VisitUrlResponse withUnknownSlugStatus(){
        return new VisitUrlResponse(null, UNKNOWN_SLUG);
    }

    public static VisitUrlResponse withInactiveSlugStatus(){
        return new VisitUrlResponse(null, INACTIVE_SLUG);
    }
}
