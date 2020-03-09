package com.jxnd.yuhaojun.blog.enums;

import lombok.Getter;

@Getter
public enum VideoCommentTypeEnum {
    Barrage(2, "弹幕"),
    Comment(1, "评论");

    private Integer type;
    private String message;

    VideoCommentTypeEnum(Integer type, String message) {
        this.type = type;
        this.message = message;
    }
}
