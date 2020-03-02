package com.jxnd.yuhaojun.blog.enums;

import lombok.Getter;

@Getter
public enum NotificationStatusEnum {
    READ(0, "已读"),
    NOT_READ(1, "未读");
    private Integer status;
    private String message;

    NotificationStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
