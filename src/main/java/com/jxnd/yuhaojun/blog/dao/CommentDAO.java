package com.jxnd.yuhaojun.blog.dao;

import com.jxnd.yuhaojun.blog.enums.CommentTypeEnum;
import com.jxnd.yuhaojun.blog.model.Comment;

import java.util.List;

public interface CommentDAO {
    void insert(Comment comment);

    Comment select(Long id);

    List<Comment> selectByComment(Integer id, CommentTypeEnum commentTypeEnum);

    void updateLikeCount(Long parentId);
}
