package com.amalvadkar.ums.common.checker;

import com.amalvadkar.ums.common.entities.HeaderMappingEntity;
import com.amalvadkar.ums.common.exceptions.PermissionDeniedException;
import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.common.repositories.HeaderMappingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EditPermissionChecker {

    private final HeaderMappingRepo headerMappingRepo;

    public void check(Long headerConfigId, Long menuId, LoggedInUser loggedInUser){
        headerMappingRepo.findHeaderMappingBy(headerConfigId, menuId, loggedInUser.roleId())
                .filter(HeaderMappingEntity::isEditable)
                .orElseThrow(PermissionDeniedException::new);
    }

}
