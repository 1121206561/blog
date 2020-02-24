package com.jxnd.yuhaojun.blog.enums;

public enum CommentTypeEnum {
    Question(1),
    Comment(2);
    private Integer type;

    public static boolean isExit(Integer type) {
        if (type == CommentTypeEnum.Question.getType() || type == CommentTypeEnum.Comment.getType()) {
            return true;
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
