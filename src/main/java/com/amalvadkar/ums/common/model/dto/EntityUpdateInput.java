package com.amalvadkar.ums.common.model.dto;

import com.amalvadkar.ums.common.entities.HeaderConfigEntity;

public record EntityUpdateInput(String tableName, String columnName, Long id, String value, LoggedInUser loggedInUser) {

    public static EntityUpdateInput from(HeaderConfigEntity headerConfigEntity, Long id, String value, LoggedInUser loggedInUser) {
        return new EntityUpdateInput(
                headerConfigEntity.getMappingTable(),
                headerConfigEntity.getMappingColumn(),
                id,
                value,
                loggedInUser
        );
    }
}