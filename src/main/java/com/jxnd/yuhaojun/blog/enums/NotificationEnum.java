package com.jxnd.yuhaojun.blog.enums;

import lombok.Getter;

@Getter
public enum NotificationEnum {
    REPLAY_QUESSTION(1, "回复了问题"),
    REPLAU_COMMENT(2, "回复了评论");

    private Integer type;
    private String message;

    NotificationEnum(Integer type, String message) {
        this.type = type;
        this.message = message;
    }
}
