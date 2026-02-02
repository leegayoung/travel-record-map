package com.travelrecord.web.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.travelrecord.web.common.exception.ErrorCode;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final T data;

    private ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("200", "Success", data);
    }

}
