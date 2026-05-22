package com.amalvadkar.ums.auth.services;

import com.amalvadkar.ums.auth.model.response.SignInResponse;
import com.amalvadkar.ums.common.model.response.CustomResponse;
import com.amalvadkar.ums.common.repositories.UrlStatusRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.amalvadkar.ums.common.enums.SignInApiMetadataMappingNameEnum.urlStatusList;
import static com.amalvadkar.ums.common.enums.UmsResponseMessageEnum.SIGNED_IN_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UrlStatusRepo urlStatusRepo;

    public CustomResponse signIn() {
        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setMetadata(prepareMetadata());
        return CustomResponse.success(signInResponse, SIGNED_IN_SUCCESSFULLY);
    }

    private Map<String, Object> prepareMetadata() {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put(urlStatusList.name(), urlStatusRepo.findUrlStatusList());
        return metadata;
    }
}
