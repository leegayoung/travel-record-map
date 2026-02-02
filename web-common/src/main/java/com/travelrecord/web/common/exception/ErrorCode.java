package com.travelrecord.web.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "Invalid Input Value"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Method Not Allowed"),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "Access is Denied"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "Duplicate email"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid password"),

    // Post
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post not found"),

    // Region
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "Region not found"),

    // Auth
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, "Unauthorized access"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
    FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "해당 리소스에 접근할 권한이 없습니다."),

    // Group
    GROUP_NOT_FOUND(HttpStatus.NOT_FOUND, "Group not found"),
    ALREADY_GROUP_MEMBER(HttpStatus.CONFLICT, "User is already a member of this group");

    private final HttpStatus status;
    private final String message;
}
