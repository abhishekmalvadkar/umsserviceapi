package com.amalvadkar.ums.common.model.response;

import lombok.Builder;
import lombok.Data;

import static com.amalvadkar.ums.common.enums.UmsHttpStatusEnum.SUCCESS;

@Data
@Builder
public class CustomResponse {
    private Object data;
    private String message;
    private int code;
    private String status;

    public static CustomResponse success(Object data, String message){
        return CustomResponse.builder()
                .data(data)
                .message(message)
                .code(SUCCESS.code())
                .status(SUCCESS.name())
                .build();
    }
}
