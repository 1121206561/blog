package com.jxnd.yuhaojun.blog.dao;

import com.jxnd.yuhaojun.blog.model.Question;

import java.util.List;

public interface ProfileDAO {
    List<Question> select(Integer offset, Integer size, String creator);

    Integer selectByCountCreator(String creator);
}
