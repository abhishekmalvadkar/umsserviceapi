package com.amalvadkar.ums.url.controllers.rest;

import com.amalvadkar.ums.common.constants.HeaderConstant;
import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.common.model.response.CustomResponse;
import com.amalvadkar.ums.url.models.request.CreateUrlRequest;
import com.amalvadkar.ums.url.models.request.FetchUrlsRequest;
import com.amalvadkar.ums.url.models.request.UpdateUrlRequest;
import com.amalvadkar.ums.url.services.*;
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
    private static final String ENDPOINT_FETCH_URLS = "/fetch-urls";
    private static final String ENDPOINT_UPDATE_URL = "/update-url";

    private final CreateUrlOnLoadService createUrlOnLoadService;
    private final CreateUrlService createUrlService;
    private final CheckSlugService checkSlugService;
    private final FetchUrlsService fetchUrlsService;
    private final UpdateUrlService updateUrlService;

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

    @PostMapping(ENDPOINT_FETCH_URLS)
    public ResponseEntity<CustomResponse> fetchUrls(
            @RequestHeader(HeaderConstant.USER_ID) Long userId,
            @RequestHeader(HeaderConstant.ROLE_ID) Long roleId,
            @RequestHeader(HeaderConstant.DEVICE) String device,
            @RequestBody FetchUrlsRequest fetchUrlsRequest
    ) {
        LoggedInUser loggedInUser = LoggedInUser.from(userId, roleId, device);
        return ResponseEntity.ok(fetchUrlsService.fetchUrls(fetchUrlsRequest, loggedInUser));
    }

    @PatchMapping(ENDPOINT_UPDATE_URL)
    public ResponseEntity<CustomResponse> updateUrl(
            @RequestHeader(HeaderConstant.USER_ID) Long userId,
            @RequestHeader(HeaderConstant.ROLE_ID) Long roleId,
            @RequestHeader(HeaderConstant.DEVICE) String device,
            @RequestBody @Valid UpdateUrlRequest updateUrlRequest
    ) {
        LoggedInUser loggedInUser = LoggedInUser.from(userId, roleId, device);
        return ResponseEntity.ok(updateUrlService.updateUrl(updateUrlRequest, loggedInUser));
    }

}
