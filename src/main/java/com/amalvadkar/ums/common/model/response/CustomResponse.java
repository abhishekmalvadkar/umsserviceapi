package com.amalvadkar.ums.common.model.response;

import com.amalvadkar.ums.common.enums.UmsResponseMessageEnum;
import lombok.Builder;
import lombok.Data;

import static com.amalvadkar.ums.common.enums.UmsResponseStatusEnum.CREATED;
import static com.amalvadkar.ums.common.enums.UmsResponseStatusEnum.SUCCESS;

@Data
@Builder
public class CustomResponse {
    private Object data;
    private String message;
    private int code;
    private String status;

    public static CustomResponse success(Object data, UmsResponseMessageEnum message){
        return CustomResponse.builder()
                .data(data)
                .message(message.value())
                .code(SUCCESS.code())
                .status(SUCCESS.name())
                .build();
    }

    public static CustomResponse created(Object data, UmsResponseMessageEnum message){
        return CustomResponse.builder()
                .data(data)
                .message(message.value())
                .code(CREATED.code())
                .status(CREATED.name())
                .build();
    }
}
