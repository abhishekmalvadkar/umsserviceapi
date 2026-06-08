package com.amalvadkar.ums.url.services;


import com.amalvadkar.ums.common.entities.HeaderConfigEntity;
import com.amalvadkar.ums.common.entities.HeaderMappingEntity;
import com.amalvadkar.ums.common.model.dto.EntityUpdateInput;
import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.common.model.response.CustomResponse;
import com.amalvadkar.ums.common.repositories.HeaderMappingRepo;
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

    private final HeaderMappingRepo headerMappingRepo;
    private final GenericUpdater genericUpdater;

    @Transactional
    public CustomResponse updateUrl(UpdateUrlRequest updateUrlRequest, LoggedInUser loggedInUser) {
        HeaderMappingEntity headerMappingEntity = headerMappingRepo.findByIdOrThrow(updateUrlRequest.headerMappingId());
        HeaderConfigEntity headerConfig = headerMappingEntity.getHeaderConfig();
        EntityUpdateInput entityUpdateInput = EntityUpdateInput.from(headerConfig, updateUrlRequest.recordId(), updateUrlRequest.value(), loggedInUser);
        genericUpdater.update(entityUpdateInput);
        return CustomResponse.success(Map.of("id", updateUrlRequest.recordId()),UPDATED_SUCCESSFULLY);
    }
}
