package com.amalvadkar.ums.auth.services;

import com.amalvadkar.ums.auth.model.response.SignInResponse;
import com.amalvadkar.ums.common.model.response.CustomResponse;
import com.amalvadkar.ums.common.repositories.UrlStatusRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UrlStatusRepo urlStatusRepo;

    public CustomResponse signIn() {
        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setMetadata(prepareMetadata());
        return CustomResponse.success(signInResponse, "Signed in successfully");
    }

    private Map<String, Object> prepareMetadata() {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("urlStatusList", urlStatusRepo.findUrlStatusList());
        return metadata;
    }
}
