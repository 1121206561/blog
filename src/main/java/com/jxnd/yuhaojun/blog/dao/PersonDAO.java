package com.jxnd.yuhaojun.blog.dao;

import com.jxnd.yuhaojun.blog.model.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> selectAll(String name);

    void insert(Person person);

    Person selectById(Long id);

    void update(Person person);
}
