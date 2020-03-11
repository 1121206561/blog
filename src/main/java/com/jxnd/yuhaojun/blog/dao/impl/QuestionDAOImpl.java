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

    @Override
    public void updateByView(Integer id) {
        mapper.updateByView(id);
    }

    @Override
    public void updateByComment(Integer id) {
        mapper.updateByComment(id);
    }

    @Override
    public List<Question> selectByTag(String tag) {
        return mapper.selectByTag(tag);
    }

    @Override
    public List<Question> selectBySeach(String seach, Integer offset, Integer size) {
        return mapper.selectBySeach(seach, offset, size);
    }

    @Override
    public Integer selectByCountSeach(String seach) {
        return mapper.selectByCountSeach(seach);
    }

    @Override
    public Integer selectByCountTag(String hotTag) {
        return mapper.selectByCountTag(hotTag);
    }

    @Override
    public Integer selectByCountSeachTag(String seach, String hotTag) {
        return mapper.selectByCountSeachTag(seach, hotTag);
    }

    @Override
    public List<Question> selectByLimitTag(Integer offset, Integer size, String hotTag) {
        return mapper.seletByLimitTag(offset, size, hotTag);
    }

    @Override
    public List<Question> selectByLimitSeachTag(Integer offset, Integer size, String hotTag, String seach) {
        return mapper.selectByLimitSeachTag(offset, seach, size, hotTag);
    }

    @Override
    public List<Question> selectPlusZero(Integer offset, Integer size) {
        return mapper.selectPlusZero(offset,size);
    }

    @Override
    public List<Question> selectPlusThirty(Integer offset, Integer size) {
        return mapper.selectPlusThirty(System.currentTimeMillis(),offset,size);
    }

    @Override
    public List<Question> selectPlusSeven(Integer offset, Integer size) {
        return mapper.selectPlusSeven(System.currentTimeMillis(),offset,size);
    }

    @Override
    public Integer selectByCountZero() {
        return mapper.selectByCountZero();
    }

    @Override
    public Integer selectByCountThirty() {
        return mapper.selectByCountThirty(System.currentTimeMillis());
    }

    @Override
    public Integer selectByCountSeven() {
        return mapper.selectByCountSeven(System.currentTimeMillis());
    }
}
