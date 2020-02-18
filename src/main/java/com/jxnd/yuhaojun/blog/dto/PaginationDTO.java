package com.jxnd.yuhaojun.blog.dto;

import lombok.Data;

import java.util.List;
 //分页信息
@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    private Integer page;
    private List<Integer> pages;
}
