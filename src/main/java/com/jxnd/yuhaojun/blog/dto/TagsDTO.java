package com.jxnd.yuhaojun.blog.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagsDTO {
    private String categoryName;
    private List<String> tags;
}
