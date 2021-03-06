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

    List<Question> selectByTag(String tag);

    List<Question> selectBySeach(String seach, Integer offset, Integer size);

    Integer selectByCountSeach(String seach);

    Integer selectByCountTag(String hotTag);

    Integer selectByCountSeachTag(String seach, String hotTag);

    List<Question> selectByLimitTag(Integer offset, Integer size, String hotTag);

    List<Question> selectByLimitSeachTag(Integer offset, Integer size, String hotTag, String seach);

    List<Question> selectPlusZero(Integer offset, Integer size);

    List<Question> selectPlusThirty(Integer offset, Integer size);

    List<Question> selectPlusSeven(Integer offset, Integer size);

    Integer selectByCountZero();

    Integer selectByCountThirty();

    Integer selectByCountSeven();
}
