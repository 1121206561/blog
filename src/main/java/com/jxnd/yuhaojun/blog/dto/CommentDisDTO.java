package com.jxnd.yuhaojun.blog.dto;

import com.jxnd.yuhaojun.blog.model.User;
import lombok.Data;

@Data
public class CommentDisDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private String commentator;
    private Long gmtCreator;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}
