package com.amalvadkar.ums.url.controllers;

import com.amalvadkar.ums.url.models.response.VisitUrlResponse;
import com.amalvadkar.ums.url.services.VisitUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/ums/urls")
@RequiredArgsConstructor
public class VisitUrlController {

    private static final String ENDPOINT_VISIT_URL = "/visit-url/{slug}";
    private final VisitUrlService visitUrlService;

    @GetMapping(ENDPOINT_VISIT_URL)
    public String visitUrl(@PathVariable String slug, Model model) {
        VisitUrlResponse visitUrlResponse = visitUrlService.visitUrl(slug);
        return switch (visitUrlResponse.urlStatusEnum()) {
            case VALID_SLUG -> forValidSlug(visitUrlResponse, slug);
            case UNKNOWN_SLUG, INACTIVE_SLUG -> forNotValidSlug(visitUrlResponse, model, slug);
        };
    }

    private static String forNotValidSlug(VisitUrlResponse visitUrlResponse, Model model, String slug) {
        log.debug("Slug {} is not valid, reason :: {}", slug, visitUrlResponse.urlStatusEnum().message());
        model.addAttribute("message", visitUrlResponse.urlStatusEnum().message());
        return "visit-url-error";
    }

    private static String forValidSlug(VisitUrlResponse visitUrlResponse, String slug) {
        log.debug("Slug {} is valid, sending original url", slug);
        return "redirect:" + visitUrlResponse.originalUrl();
    }

}
