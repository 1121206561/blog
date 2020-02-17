package com.jxnd.yuhaojun.blog.dao.impl;

import com.jxnd.yuhaojun.blog.Mapper.UserMapper;
import com.jxnd.yuhaojun.blog.dao.UserDAO;
import com.jxnd.yuhaojun.blog.dto.GithubUserDTO;
import com.jxnd.yuhaojun.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class UserDAOImpl implements UserDAO {
    @Autowired
    private UserMapper mapper;

    //把信息保存在Mysql中
    @Override
    public void insert(GithubUserDTO githubUserDTO, HttpServletResponse response) {
        User user = new User();
        user.setAccount_id(String.valueOf(githubUserDTO.getId()));
        user.setLogin(githubUserDTO.getLogin());
        String token = UUID.randomUUID().toString();
        response.addCookie(new Cookie("token", token));
        user.setToken(token);
        user.setGmt_create(System.currentTimeMillis());
        user.setGmt_modified(user.getGmt_create());
        user.setAvatar_url(githubUserDTO.getAvatar_url());
        mapper.insert(user);
    }

    @Override
    public User select(String token) {
        return mapper.select(token);
    }
}
