package com.lamdayne.ecommercelaptop.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(1000, "Uncategorized error"),
    INVALID_KEY(1001, "Invalid key error"),
    USER_NOT_FOUND(1002, "User not found"),
    EMAIL_EXISTS(1003, "Email already exists"),
    PHONE_EXISTS(1004, "Phone already exists"),
    INVALID_PASSWORD(1005, "Invalid password"),
    INVALID_PHONE(1006, "Invalid phone number"),
    FIRST_NAME_BLANK(1007, "First name cannot blank"),
    ;
    private int code;
    private String message;
    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
