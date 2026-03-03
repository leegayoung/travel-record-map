package com.travelrecord.web.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.travelrecord.web.common.exception.ErrorCode;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 1. Jackson을 위한 기본 생성자 추가
public class ApiResponse<T> {

  private String code;    // 2. final 제거 (JSON 역직렬화를 위해)
  private String message;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private T data;

  // 기존의 생성자 (성공/실패 정적 팩토리 메소드용으로 유지)
  private ApiResponse(String code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>("200", "Success", data);
  }

  public static <T> ApiResponse<T> error(String code, String message) {
    return new ApiResponse<>(code, message, null);
  }
}