package com.jxnd.yuhaojun.blog.dto;

import lombok.Data;

@Data
public class videoCommentDTO {
    private Long parent_aid;
    private String content;
    private Integer type;
}
