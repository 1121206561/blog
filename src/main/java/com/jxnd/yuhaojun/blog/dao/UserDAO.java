package com.jxnd.yuhaojun.blog.dao;

import com.jxnd.yuhaojun.blog.model.User;

public interface UserDAO {
    void insert(User user);

    User select(String token);

    User selectByCreator(String creator);

    User selectByAccount(Long id);

    void update(User user);
}
