package com.amalvadkar.ums.url.services;

import com.amalvadkar.ums.common.HeaderHelper;
import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.common.model.response.CustomResponse;
import com.amalvadkar.ums.common.model.response.HeaderResponse;
import com.amalvadkar.ums.url.models.response.CreateUrlOnLoadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.amalvadkar.ums.common.enums.UmsResponseMessageEnum.FETCHED_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateUrlOnLoadService {

    private final HeaderHelper headerHelper;

    public CustomResponse createUrlOnLoad(LoggedInUser loggedInUser) {
        List<HeaderResponse> headers = headerHelper.findHeaders(loggedInUser, 2L);
        return CustomResponse.success(CreateUrlOnLoadResponse.from(headers), FETCHED_SUCCESSFULLY);
    }
}
