package com.amalvadkar.ums.url.services;


import com.amalvadkar.ums.url.entities.UrlEntity;
import com.amalvadkar.ums.url.models.response.VisitUrlResponse;
import com.amalvadkar.ums.url.repositories.UrlRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitUrlService {

    private final UrlRepo urlRepo;

    public VisitUrlResponse visitUrl(String slug) {
        return urlRepo.findBySlug(slug)
                .map(this::toResponse)
                .orElseGet(VisitUrlResponse::withUnknownSlugStatus);
    }

    private VisitUrlResponse toResponse(UrlEntity url) {
        if (url.isInActive()) {
            return VisitUrlResponse.withInactiveSlugStatus();
        }
        return VisitUrlResponse.withValidSlugStatus(url);
    }
}
