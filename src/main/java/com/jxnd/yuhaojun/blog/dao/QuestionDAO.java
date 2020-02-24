package com.jxnd.yuhaojun.blog.dao;

import com.jxnd.yuhaojun.blog.model.Question;

import java.util.List;

public interface QuestionDAO {
    void insert(Question question);

    List<Question> select(Integer offset, Integer size);

    Integer selectByCount();

    Question selectById(Integer id);

    void update(Question question);

    void updateByView(Integer id);

    void updateByComment(Integer id);
}
