package com.amalvadkar.ums.common.model.response;

import com.amalvadkar.ums.common.enums.UmsResponseMessageEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static com.amalvadkar.ums.common.enums.UmsResponseStatusEnum.*;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class CustomResponse {
    private Object data;
    private String message;
    private int code;
    private String status;
    private boolean success;
    private List<String> errors;

    public static CustomResponse success(Object data, UmsResponseMessageEnum message){
        return CustomResponse.builder()
                .data(data)
                .message(message.value())
                .code(SUCCESS.code())
                .success(true)
                .status(SUCCESS.name())
                .build();
    }

    public static CustomResponse created(Object data, UmsResponseMessageEnum message){
        return CustomResponse.builder()
                .data(data)
                .message(message.value())
                .code(CREATED.code())
                .success(true)
                .status(CREATED.name())
                .build();
    }

    public static CustomResponse badRequest(List<String> errorMessages) {
        return CustomResponse.builder()
                .code(BAD_REQUEST.code())
                .success(false)
                .status(BAD_REQUEST.name())
                .errors(errorMessages)
                .build();
    }

    public static CustomResponse permissionDenied(String errorMessages) {
        return CustomResponse.builder()
                .code(PERMISSION_DENIED.code())
                .success(false)
                .status(PERMISSION_DENIED.name())
                .errors(List.of(errorMessages))
                .build();
    }
}
