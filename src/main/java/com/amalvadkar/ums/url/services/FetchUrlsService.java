package com.amalvadkar.ums.url.services;

import com.amalvadkar.ums.common.helpers.HeaderHelper;
import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.common.model.response.CustomResponse;
import com.amalvadkar.ums.url.models.request.FetchUrlsRequest;
import com.amalvadkar.ums.url.models.response.FetchUrlsResponse;
import com.amalvadkar.ums.url.preparer.FetchUrlsDataPreparer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.amalvadkar.ums.common.enums.MenuEnum.MY_SHORT_URLS;
import static com.amalvadkar.ums.common.enums.UmsResponseMessageEnum.FETCHED_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FetchUrlsService {

    private final HeaderHelper headerHelper;
    private final FetchUrlsDataPreparer fetchUrlsDataPreparer;

    public CustomResponse fetchUrls(FetchUrlsRequest fetchUrlsRequest, LoggedInUser loggedInUser) {
        FetchUrlsResponse fetchUrlsResponse = new FetchUrlsResponse();
        fetchUrlsResponse.setHeaders(headerHelper.findHeaders(loggedInUser, MY_SHORT_URLS.id()));
        fetchUrlsResponse.setData(fetchUrlsDataPreparer.prepare(fetchUrlsRequest, loggedInUser));
        return CustomResponse.success(fetchUrlsResponse, FETCHED_SUCCESSFULLY);
    }
}
