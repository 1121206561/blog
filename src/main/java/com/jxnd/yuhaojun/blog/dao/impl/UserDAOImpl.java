package com.jxnd.yuhaojun.blog.dao.impl;

import com.jxnd.yuhaojun.blog.Mapper.UserMapper;
import com.jxnd.yuhaojun.blog.dao.UserDAO;
import com.jxnd.yuhaojun.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO {
    @Autowired
    private UserMapper mapper;

    //把信息保存在Mysql中
    @Override
    public void insert(User user) {
        mapper.insert(user);
    }

    @Override
    public User select(String token) {
        return mapper.select(token);
    }

    @Override
    public User selectByCreator(String creator) {
        return mapper.selectByCreator(creator);
    }

    @Override
    public User selectByAccount(Long id) {
        return mapper.selectByAccount(id);
    }

    @Override
    public void update(User user) {
        mapper.update(user);
    }
}
