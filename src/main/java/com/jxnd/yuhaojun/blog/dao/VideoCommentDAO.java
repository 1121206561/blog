package com.jxnd.yuhaojun.blog.dao;

import com.jxnd.yuhaojun.blog.model.VideoComment;

import java.util.List;

public interface VideoCommentDAO {
    List<VideoComment> selectALL(Long aid);

    void insert(VideoComment videoComment);
}
