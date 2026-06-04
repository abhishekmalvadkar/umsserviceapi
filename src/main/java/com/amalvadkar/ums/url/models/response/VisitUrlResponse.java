package com.amalvadkar.ums.url.models.response;

import com.amalvadkar.ums.url.entities.UrlEntity;
import com.amalvadkar.ums.url.enums.VisitUrlStatusEnum;

import static com.amalvadkar.ums.url.enums.VisitUrlStatusEnum.*;

public record VisitUrlResponse(String originalUrl, VisitUrlStatusEnum urlStatusEnum) {

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
