package com.jxnd.yuhaojun.blog.dao.impl;

import com.jxnd.yuhaojun.blog.Mapper.QuestionMapper;
import com.jxnd.yuhaojun.blog.dao.ProfileDAO;
import com.jxnd.yuhaojun.blog.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileDAOImpl implements ProfileDAO {
    @Autowired
    private QuestionMapper mapper;

    @Override
    public List<Question> select(Integer offset, Integer size, String creator) {
        List<Question> list = mapper.selectByCreator(offset, size, creator);
        return list;
    }

    @Override
    public Integer selectByCountCreator(String creator) {
        Integer count = mapper.selectByCountCreator(creator);
        return count;
    }
}
