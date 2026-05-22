package com.amalvadkar.ums.common;

import com.amalvadkar.ums.common.entities.HeaderConfigEntity;
import com.amalvadkar.ums.common.entities.HeaderMappingEntity;
import com.amalvadkar.ums.common.entities.OptionSourceEntity;
import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.common.model.response.HeaderResponse;
import com.amalvadkar.ums.common.repositories.HeaderMappingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HeaderHelper {

    private final HeaderMappingRepo headerMappingRepo;

    public List<HeaderResponse> findHeaders(LoggedInUser loggedInUser, Long menuId){
        List<HeaderMappingEntity> headerMappings = headerMappingRepo.findHeaderMappings(loggedInUser.roleId(), menuId);
        return headerMappings.stream()
                .map(this::toHeaderResponse)
                .toList();
    }

    private HeaderResponse toHeaderResponse(HeaderMappingEntity headerMappingEntity) {
        HeaderResponse headerResponse = new HeaderResponse();
        HeaderConfigEntity headerConfig = headerMappingEntity.getHeaderConfig();
        OptionSourceEntity optionSource = headerConfig.getOptionSource();
        headerResponse.setId(headerConfig.getId());
        headerResponse.setDisplayName(headerConfig.getHeaderName());
        headerResponse.setMappingName(headerConfig.getMappingName());
        headerResponse.setHeaderType(headerConfig.getHeaderType());
        headerResponse.setHeaderMappingId(headerMappingEntity.getId());
        headerResponse.setEditable(headerMappingEntity.isEditable());
        headerResponse.setFilterable(headerConfig.isFilterable());
        headerResponse.setSortable(headerConfig.isSortable());
        headerResponse.setOptionSource(optionSource.getMappingName());
        return headerResponse;
    }

}
