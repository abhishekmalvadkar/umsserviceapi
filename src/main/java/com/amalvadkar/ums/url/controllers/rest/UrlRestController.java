package com.amalvadkar.ums.url.controllers.rest;

import com.amalvadkar.ums.common.constants.HeaderConstant;
import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.common.model.response.CustomResponse;
import com.amalvadkar.ums.url.services.CreateUrlOnLoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ums/urls")
@RequiredArgsConstructor
public class UrlRestController {

    private static final String ENDPOINT_CREATE_URL_ON_LOAD = "/create-url-on-load";

    private final CreateUrlOnLoadService createUrlOnLoadService;

    @GetMapping(ENDPOINT_CREATE_URL_ON_LOAD)
    public ResponseEntity<CustomResponse> createUrlOnLoad(
            @RequestHeader(HeaderConstant.USER_ID) Long userId,
            @RequestHeader(HeaderConstant.ROLE_ID) Long roleId,
            @RequestHeader(HeaderConstant.DEVICE) String device
    ) {
        LoggedInUser loggedInUser = LoggedInUser.from(userId, roleId, device);
        return ResponseEntity.ok(createUrlOnLoadService.createUrlOnLoad(loggedInUser));
    }

}
