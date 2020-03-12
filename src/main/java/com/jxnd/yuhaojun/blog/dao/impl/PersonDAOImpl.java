package com.jxnd.yuhaojun.blog.dao.impl;

import com.jxnd.yuhaojun.blog.Mapper.PersonMapper;
import com.jxnd.yuhaojun.blog.dao.PersonDAO;
import com.jxnd.yuhaojun.blog.model.Person;
import com.jxnd.yuhaojun.blog.model.PersonExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAOImpl implements PersonDAO {
    @Autowired
    private PersonMapper personMapper;

    @Override
    public List<Person> selectAll(String name) {
        PersonExample personExample = new PersonExample();
        personExample.createCriteria().andNameEqualTo(name);
        return personMapper.selectByExample(personExample);
    }

    @Override
    public void insert(Person person) {
        personMapper.insert(person);
    }

    @Override
    public Person selectById(Long id) {
        return personMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Person person) {
        PersonExample personExample = new PersonExample();
        personExample.createCriteria().andIdEqualTo(person.getId());
        personMapper.updateByExample(person, personExample);
    }
}
