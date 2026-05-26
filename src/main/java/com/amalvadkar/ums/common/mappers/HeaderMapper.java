package com.amalvadkar.ums.common.mappers;

import com.amalvadkar.ums.common.entities.HeaderMappingEntity;
import com.amalvadkar.ums.common.model.response.HeaderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface HeaderMapper {

    @Mapping(source = "headerConfig.id", target = "id")
    @Mapping(source = "headerConfig.headerName", target = "displayName")
    @Mapping(source = "headerConfig.mappingName", target = "mappingName")
    @Mapping(source = "headerConfig.headerType", target = "headerType")
    @Mapping(source = "id", target = "headerMappingId")
    @Mapping(source = "headerConfig.filterable", target = "filterable")
    @Mapping(source = "headerConfig.sortable", target = "sortable")
    @Mapping(source = "headerConfig.optionSource.mappingName", target = "optionSource")
    HeaderResponse toHeaderResponse(HeaderMappingEntity entity);

    List<HeaderResponse> toHeaderResponseList(List<HeaderMappingEntity> entities);

}
