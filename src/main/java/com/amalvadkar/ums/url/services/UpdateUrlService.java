package com.amalvadkar.ums.url.services;


import com.amalvadkar.ums.common.aspects.EditPermissionCheck;
import com.amalvadkar.ums.common.entities.HeaderConfigEntity;
import com.amalvadkar.ums.common.enums.MenuEnum;
import com.amalvadkar.ums.common.model.dto.EntityUpdateInput;
import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.common.model.response.CustomResponse;
import com.amalvadkar.ums.common.repositories.HeaderConfigRepo;
import com.amalvadkar.ums.common.updater.GenericUpdater;
import com.amalvadkar.ums.url.models.request.UpdateUrlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.amalvadkar.ums.common.enums.UmsResponseMessageEnum.UPDATED_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UpdateUrlService {

    private final GenericUpdater genericUpdater;
    private final HeaderConfigRepo headerConfigRepo;

    @Transactional
    @EditPermissionCheck(MenuEnum.MY_SHORT_URLS)
    public CustomResponse updateUrl(UpdateUrlRequest updateUrlRequest, LoggedInUser loggedInUser) {
        HeaderConfigEntity headerConfig = headerConfigRepo.findByIdOrThrow(updateUrlRequest.headerConfigId());
        genericUpdater.update(prepareEntityUpdateInput(updateUrlRequest, loggedInUser, headerConfig));
        return CustomResponse.success(Map.of("id", updateUrlRequest.recordId()),UPDATED_SUCCESSFULLY);
    }

    private static EntityUpdateInput prepareEntityUpdateInput(UpdateUrlRequest updateUrlRequest, LoggedInUser loggedInUser, HeaderConfigEntity headerConfig) {
        return EntityUpdateInput.from(headerConfig, updateUrlRequest.recordId(), updateUrlRequest.value(), loggedInUser);
    }
}
