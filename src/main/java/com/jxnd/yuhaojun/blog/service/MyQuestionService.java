package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.dao.QuestionDAO;
import com.jxnd.yuhaojun.blog.dao.UserDAO;
import com.jxnd.yuhaojun.blog.dto.QuestionDTO;
import com.jxnd.yuhaojun.blog.model.Question;
import com.jxnd.yuhaojun.blog.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyQuestionService {
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private UserDAO userDAO;

    public void updateByView(Integer id) {
        questionDAO.updateByView(id);
    }

    public Question selectById(Integer id) {
        Question question = questionDAO.selectById(id);
        return question;
    }

    public Set<QuestionDTO> selectByTag(Integer id) {
        Question question = questionDAO.selectById(id);
        String[] tags = question.getTag().split(",");
        Set<QuestionDTO> questionDTOSet = new HashSet<>();
        for (String tag : tags) {
            List<Question> questionTags = questionDAO.selectByTag(tag);
            for (Question question1 : questionTags) {
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question1, questionDTO);
                User user = userDAO.selectByCreator(question1.getCreator());
                questionDTO.setUser(user);
                if (questionDTO.getId() != id) {
                    questionDTOSet.add(questionDTO);
                }
            }
        }
        return questionDTOSet;
    }
}
