package com.lamdayne.ecommercelaptop.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(1000, "Uncategorized error"),
    INVALID_KEY(1001, "Invalid key error"),
    USER_NOT_FOUND(1002, "User not found"),
    EMAIL_EXISTS(1003, "Email already exists"),
    PHONE_EXISTS(1004, "Phone already exists"),
    INVALID_PASSWORD(1005, "Password must be at least 8 characters"),
    INVALID_PHONE(1006, "Invalid phone number"),
    FIRST_NAME_BLANK(1007, "First name cannot blank"),
    EMAIL_BLANK(1008, "Email cannot blank"),
    FILE_REQUIRED(1009, "File not found"),
    CATEGORY_NAME_BLANK(1010, "Category name cannot be blank"),
    CATEGORY_ALREADY_EXISTS(1011, "Category name already exists"),
    CATEGORY_NOT_FOUND(1012, "Category not found"),
    BRAND_NAME_BLANK(1013, "Brand name cannot blank"),
    BRAND_ALREADY_EXISTS(1014, "Brand cannot already exists"),
    BRAND_NOT_FOUND(1015, "Brand not found"),
    PRODUCT_NOT_FOUND(1016, "Product not found"),
    PRODUCT_NAME_BLANK(1017, "Product name cannot be blank"),
    PRODUCT_PRICE_NULL(1018, "Product price cannot null"),
    PRODUCT_STOCK_NULL(1019, "Product stock cannot null"),
    PRODUCT_DESCRIPTION_BLANK(1020, "Product description cannot be blank"),
    PRODUCT_STATUS_NULL(1021, "Product status cannot null"),
    CATEGORY_ID_NULL(1022, "Category ID cannot null"),
    BRAND_ID_NULL(1023, "Brand ID cannot null"),
    CART_NOT_FOUND(1024, "Cart not found"),
    CART_USER_ID_BLANK(1025, "User ID cannot be blank"),
    CART_PRODUCT_ID_BLANK(1026, "Product ID cannot be blank"),
    PRODUCT_QUANTITY_NULL(1027, "Product quantity cannot null"),
    ;
    private int code;
    private String message;
    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
