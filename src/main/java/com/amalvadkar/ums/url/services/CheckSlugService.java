package com.amalvadkar.ums.url.services;


import com.amalvadkar.ums.common.model.response.CustomResponse;
import com.amalvadkar.ums.url.repositories.UrlRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.amalvadkar.ums.common.enums.UmsResponseMessageEnum.CHECKED_SUCCESSFULLY;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CheckSlugService {

    private final UrlRepo urlRepo;

    public CustomResponse check(String slug) {
        boolean slugAlreadyExists = urlRepo.existsBySlug(slug);
        log.debug("Slug already exists :: {}", slugAlreadyExists);
        return CustomResponse.success(
                Map.of("slugAlreadyExists", slugAlreadyExists),
                CHECKED_SUCCESSFULLY
        );
    }
}
