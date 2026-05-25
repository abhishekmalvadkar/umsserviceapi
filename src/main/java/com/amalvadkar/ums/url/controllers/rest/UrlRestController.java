package com.amalvadkar.ums.url.controllers.rest;

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

    private final CreateUrlOnLoadService createUrlOnLoadService;

    @GetMapping("/create-url-on-load")
    public ResponseEntity<CustomResponse> createUrlOnLoad(
            @RequestHeader("userid") Long userId,
            @RequestHeader("roleid") Long roleId,
            @RequestHeader("device") String device
    ) {
        LoggedInUser loggedInUser = new LoggedInUser(userId, roleId, device);
        return ResponseEntity.ok(createUrlOnLoadService.createUrlOnLoad(loggedInUser));
    }

}
