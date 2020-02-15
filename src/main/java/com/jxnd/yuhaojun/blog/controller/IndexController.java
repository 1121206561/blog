package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.dao.UserDAO;
import com.jxnd.yuhaojun.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserDAO dao;

    @GetMapping("/index")
    public String hello(HttpServletRequest request) {
        //每次进入主页面时就会对数据库进行查询是否你已经成功登录过
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = dao.select(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        return "index";
    }
}
