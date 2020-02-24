package com.jxnd.yuhaojun.blog.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long parent_id;
    private String content;
    private Integer type;
}
