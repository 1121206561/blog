package com.jxnd.yuhaojun.blog.dto;

import lombok.Data;

import java.util.ArrayList;
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
    private Integer totalCount;
    private List<Integer> pages = new ArrayList<>();

    public void setpagination(int totalCount, Integer page) {
        this.page = page;
        this.totalCount = totalCount;
        //计算分页框
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i >= 1) {
                pages.add(0, page - i);
            }
            if (page + i <= totalCount) {
                pages.add(page + i);
            }
        }
        //计算是否有下一页
        if (page == totalCount) {
            showNext = false;
        } else {
            showNext = true;
        }
        //计算是否有上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //计算是否有第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //计算是否有最后一页
        if (pages.contains(totalCount)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
