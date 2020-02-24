package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.dao.QuestionDAO;
import com.jxnd.yuhaojun.blog.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyQuestionService {
    @Autowired
    private QuestionDAO questionDAO;

    public void updateByView(Integer id) {
        questionDAO.updateByView(id);
    }

    public Question selectById(Integer id) {
        Question question = questionDAO.selectById(id);
        return question;
    }
}
