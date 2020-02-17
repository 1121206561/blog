package com.jxnd.yuhaojun.blog.model;

import lombok.Data;
 //封装帖子的所有信息
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modified;
    private String creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private String tag;
}
