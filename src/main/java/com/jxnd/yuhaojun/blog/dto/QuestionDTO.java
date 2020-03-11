package com.jxnd.yuhaojun.blog.dto;

import com.jxnd.yuhaojun.blog.model.User;
import lombok.Data;

//包含question信息和user信息
@Data
public class QuestionDTO {
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
    private User user;

}
