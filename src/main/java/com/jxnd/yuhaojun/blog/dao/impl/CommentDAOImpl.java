package com.jxnd.yuhaojun.blog.dao.impl;

import com.jxnd.yuhaojun.blog.Mapper.CommentMapper;
import com.jxnd.yuhaojun.blog.dao.CommentDAO;
import com.jxnd.yuhaojun.blog.enums.CommentTypeEnum;
import com.jxnd.yuhaojun.blog.model.Comment;
import com.jxnd.yuhaojun.blog.model.CommentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public List<Comment> selectByComment(Integer id, CommentTypeEnum commentTypeEnum) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(Long.valueOf(id.toString()))
                .andTypeEqualTo(commentTypeEnum.getType());
        //按倒序排序
        commentExample.setOrderByClause("gmt_creator desc");
        List<Comment> list = mapper.selectByExample(commentExample);
        return list;
    }
}
