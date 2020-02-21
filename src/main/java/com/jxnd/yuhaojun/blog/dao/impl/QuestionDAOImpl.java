package com.jxnd.yuhaojun.blog.dao.impl;

import com.jxnd.yuhaojun.blog.Mapper.QuestionMapper;
import com.jxnd.yuhaojun.blog.dao.QuestionDAO;
import com.jxnd.yuhaojun.blog.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionDAOImpl implements QuestionDAO {
    @Autowired
    private QuestionMapper mapper;

    @Override
    public void insert(Question question) {
        if (question != null) {
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            mapper.insert(question);
        }
    }

    @Override
    public List<Question> select(Integer offset, Integer size) {
        List<Question> list = mapper.select(offset, size);
        return list;
    }

    @Override
    public Integer selectByCount() {
        return mapper.selectByCount();
    }

    @Override
    public Question selectById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public void update(Question question) {
        mapper.update(question);
    }
}
