package com.amalvadkar.ums.url.mappers;

import com.amalvadkar.ums.url.entities.UrlEntity;
import com.amalvadkar.ums.url.models.response.CreateUrlResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UrlMapper {

    @Mapping(source = "urlStatus.id", target = "urlStatusId")
    @Mapping(source = "createdBy.id", target = "createdByUserId")
    CreateUrlResponse toCreateUrlResponse(UrlEntity entity);
}
