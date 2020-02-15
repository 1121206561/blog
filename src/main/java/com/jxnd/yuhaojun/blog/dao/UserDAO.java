package com.jxnd.yuhaojun.blog.dao;

import com.jxnd.yuhaojun.blog.dto.GithubUserDTO;
import com.jxnd.yuhaojun.blog.model.User;

import javax.servlet.http.HttpServletResponse;

public interface UserDAO {
    void insert(GithubUserDTO githubUserDTO, HttpServletResponse response);

    User select(String token);
}
