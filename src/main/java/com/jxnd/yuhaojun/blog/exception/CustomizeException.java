package com.jxnd.yuhaojun.blog.exception;

import lombok.Getter;

@Getter
public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(CustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }
}
