package com.jxnd.yuhaojun.blog.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "你找的问题不存在,要不换一个试试"),
    TARGET_NOT_FOUND(2002, "未选中任何问题进行评论"),
    NO_LOGIN(2003, "当前操作需要登陆,请登录后重试"),
    SYS_ERROR(2004, "服务冒烟了,请稍后再来访问！！！！"),
    TYPE_NOT_FOUND(2005, "评论类型不正确,请重新评论"),
    COMMENT_NOT_FOUND(2006, "你找的评论不存在,请重新评论"),
    NO_CONTENT(2007, "评论不能为空！！！"),
    NO_COMMENT_DELETE(2008, "这条评论不存在或已被删除"),
    NOT_YOUR_QUESTION(2009,"不要查看别人的东西亲！"),
    NO_UPLOAD_IMG(2010,"抱歉,上传图片失败,请重试！！")
    ;

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
