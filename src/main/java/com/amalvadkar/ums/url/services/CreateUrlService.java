package com.amalvadkar.ums.url.services;

import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.common.model.response.CustomResponse;
import com.amalvadkar.ums.common.repositories.UrlStatusRepo;
import com.amalvadkar.ums.common.repositories.UserRepo;
import com.amalvadkar.ums.url.entities.UrlEntity;
import com.amalvadkar.ums.url.generator.SlugGenerator;
import com.amalvadkar.ums.url.mappers.UrlMapper;
import com.amalvadkar.ums.url.models.request.CreateUrlRequest;
import com.amalvadkar.ums.url.models.response.CreateUrlResponse;
import com.amalvadkar.ums.url.repositories.UrlRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.amalvadkar.ums.common.enums.UmsResponseMessageEnum.CREATED_SUCCESSFULLY;
import static com.amalvadkar.ums.url.enums.UrlStatusEnum.ACTIVE;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CreateUrlService {

    private final UrlStatusRepo urlStatusRepo;
    private final UserRepo userRepo;
    private final UrlRepo urlRepo;
    private final UrlMapper urlMapper;
    private final SlugGenerator slugGenerator;

    @Transactional
    public CustomResponse createUrl(CreateUrlRequest createUrlRequest, LoggedInUser loggedInUser) {
        UrlEntity savedUrlEntity = saveUrl(createUrlRequest, loggedInUser);
        CreateUrlResponse createUrlResponse = urlMapper.toCreateUrlResponse(savedUrlEntity);
        return CustomResponse.created(createUrlResponse, CREATED_SUCCESSFULLY);
    }

    private UrlEntity saveUrl(CreateUrlRequest createUrlRequest, LoggedInUser loggedInUser) {
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setTitle(createUrlRequest.getTitle());
        urlEntity.setOriginalUrl(createUrlRequest.getOriginalUrl());
        urlEntity.setUrlStatus(urlStatusRepo.getReferenceById(ACTIVE.id()));
        urlEntity.setCreatedBy(userRepo.getReferenceById(loggedInUser.userId()));
        urlEntity.setSlug(prepareSlug(createUrlRequest));
        return urlRepo.save(urlEntity);
    }

    private String prepareSlug(CreateUrlRequest createUrlRequest) {
        if (createUrlRequest.hasSlug()) {
            return createUrlRequest.getSlug();
        }
        return slugGenerator.generate();
    }
}
