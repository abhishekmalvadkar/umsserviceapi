package com.amalvadkar.ums.url.services;


import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.common.model.response.CustomResponse;
import com.amalvadkar.ums.url.models.request.UpdateUrlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UpdateUrlService {

    @Transactional
    public CustomResponse updateUrl(UpdateUrlRequest updateUrlRequest, LoggedInUser loggedInUser) {
        //TODO: Fetch header config by headerMappingId
        //TODO: Find entity which needs to update using mappingTable from headerConfig using JPA dynamic meta model and reflection
        //TODO: Find entity field which needs to update using mappingColumn from headerConfig using JPA dynamic meta model and reflection
        //TODO: Convert incoming string value into found entity type based on field type
        //TODO: Fetch dynamic found entity using incoming recordId
        //TODO: Update found entity field with converted value
        //TODO: Update it in generic way
        return null;
    }
}
