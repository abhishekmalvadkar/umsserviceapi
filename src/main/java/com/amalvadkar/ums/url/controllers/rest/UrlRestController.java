package com.amalvadkar.ums.url.controllers.rest;

import com.amalvadkar.ums.common.constants.HeaderConstant;
import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.common.model.response.CustomResponse;
import com.amalvadkar.ums.url.models.request.CreateUrlRequest;
import com.amalvadkar.ums.url.services.CheckSlugService;
import com.amalvadkar.ums.url.services.CreateUrlOnLoadService;
import com.amalvadkar.ums.url.services.CreateUrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ums/urls")
@RequiredArgsConstructor
public class UrlRestController {

    private static final String ENDPOINT_CREATE_URL_ON_LOAD = "/create-url-on-load";
    private static final String ENDPOINT_CREATE_URL = "/create-url";
    private static final String ENDPOINT_CHECK_SLUG = "/check-slug";

    private final CreateUrlOnLoadService createUrlOnLoadService;
    private final CreateUrlService createUrlService;
    private final CheckSlugService checkSlugService;

    @GetMapping(ENDPOINT_CREATE_URL_ON_LOAD)
    public ResponseEntity<CustomResponse> createUrlOnLoad(
            @RequestHeader(HeaderConstant.USER_ID) Long userId,
            @RequestHeader(HeaderConstant.ROLE_ID) Long roleId,
            @RequestHeader(HeaderConstant.DEVICE) String device
    ) {
        LoggedInUser loggedInUser = LoggedInUser.from(userId, roleId, device);
        return ResponseEntity.ok(createUrlOnLoadService.createUrlOnLoad(loggedInUser));
    }

    @PostMapping(ENDPOINT_CREATE_URL)
    public ResponseEntity<CustomResponse> createUrl(
            @RequestHeader(HeaderConstant.USER_ID) Long userId,
            @RequestHeader(HeaderConstant.ROLE_ID) Long roleId,
            @RequestHeader(HeaderConstant.DEVICE) String device,
            @RequestBody @Valid CreateUrlRequest createUrlRequest
    ) {
        LoggedInUser loggedInUser = LoggedInUser.from(userId, roleId, device);
        return ResponseEntity.ok(createUrlService.createUrl(createUrlRequest, loggedInUser));
    }

    @GetMapping(ENDPOINT_CHECK_SLUG)
    public ResponseEntity<CustomResponse> checkSlug(@RequestParam("slug") String slug) {
        return ResponseEntity.ok(checkSlugService.check(slug));
    }

}
