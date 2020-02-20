package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.Mapper.QuestionMapper;
import com.jxnd.yuhaojun.blog.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyQuestionService {
    @Autowired
    private QuestionMapper mapper;

    public Question selectById(Integer id) {
        Question question = mapper.selectById(id);
        return question;
    }
}
