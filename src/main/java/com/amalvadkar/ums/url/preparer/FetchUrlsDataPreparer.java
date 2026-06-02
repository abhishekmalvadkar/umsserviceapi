package com.amalvadkar.ums.url.preparer;

import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.url.entities.UrlEntity;
import com.amalvadkar.ums.url.mappers.UrlMapper;
import com.amalvadkar.ums.url.models.request.FetchUrlsRequest;
import com.amalvadkar.ums.url.models.response.FetchUrlsDataResponse;
import com.amalvadkar.ums.url.repositories.UrlRepo;
import com.amalvadkar.ums.url.specifications.UrlSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FetchUrlsDataPreparer {

    private final UrlRepo urlRepo;
    private final UrlMapper urlMapper;

    public List<FetchUrlsDataResponse> prepare(FetchUrlsRequest fetchUrlsRequest, LoggedInUser loggedInUser) {
        List<UrlEntity> urlEntities = urlRepo.findAll(UrlSpecification.byFilter(fetchUrlsRequest, loggedInUser));
        return urlMapper.toFetchUrlDataResponseList(urlEntities);
    }
}
