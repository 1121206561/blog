package com.jxnd.yuhaojun.blog.dto;

import com.jxnd.yuhaojun.blog.model.User;
import lombok.Data;

@Data
public class VideosCommentDTO {
    private Long id;
    private Integer type;
    private String commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private String content;
    private Long parentAid;
    private User user;
}

