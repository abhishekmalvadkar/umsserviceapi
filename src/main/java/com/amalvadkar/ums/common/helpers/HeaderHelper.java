package com.amalvadkar.ums.common.helpers;

import com.amalvadkar.ums.common.entities.HeaderMappingEntity;
import com.amalvadkar.ums.common.mappers.HeaderMapper;
import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.common.model.response.HeaderResponse;
import com.amalvadkar.ums.common.repositories.HeaderMappingRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class HeaderHelper {

    private final HeaderMappingRepo headerMappingRepo;
    private final HeaderMapper headerMapper;

    public List<HeaderResponse> findHeaders(LoggedInUser loggedInUser, Long menuId){
        List<HeaderMappingEntity> headerMappings = headerMappingRepo.findHeaderMappings(loggedInUser.roleId(), menuId);
        log.debug("Total Headers :: {} for role id :: {} and menu id :: {}", headerMappings.size(), loggedInUser.roleId(), menuId);
        return headerMapper.toHeaderResponseList(headerMappings);
    }

}
