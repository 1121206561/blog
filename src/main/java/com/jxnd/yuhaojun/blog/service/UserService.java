package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.dao.UserDAO;
import com.jxnd.yuhaojun.blog.dto.GithubUserDTO;
import com.jxnd.yuhaojun.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public void service(GithubUserDTO githubUserDTO, HttpServletResponse response) {
        User accountUser = userDAO.selectByAccount(githubUserDTO.getId());

        User user = new User();
        String token = UUID.randomUUID().toString();
        response.addCookie(new Cookie("token", token));
        user.setToken(token);
        user.setGmt_create(System.currentTimeMillis());
        user.setGmt_modified(user.getGmt_create());
        user.setAccount_id(String.valueOf(githubUserDTO.getId()));
        if (accountUser == null) {
            user.setLogin(githubUserDTO.getLogin());
            user.setAvatar_url(githubUserDTO.getAvatar_url());
            userDAO.insert(user);
        } else {
            userDAO.update(user);
        }

    }
}
