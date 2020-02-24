package com.jxnd.yuhaojun.blog.dao.impl;

import com.jxnd.yuhaojun.blog.Mapper.CommentMapper;
import com.jxnd.yuhaojun.blog.dao.CommentDAO;
import com.jxnd.yuhaojun.blog.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentDAOImpl implements CommentDAO {
    @Autowired
    private CommentMapper mapper;

    @Override
    public void insert(Comment comment) {
        mapper.insert(comment);
    }

    @Override
    public Comment select(Long id) {
        return mapper.selectByPrimaryKey(id);
    }
}
