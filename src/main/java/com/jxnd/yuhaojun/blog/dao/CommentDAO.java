package com.jxnd.yuhaojun.blog.dao;

import com.jxnd.yuhaojun.blog.model.Comment;

public interface CommentDAO {
    void insert(Comment comment);

    Comment select(Long id);
}
