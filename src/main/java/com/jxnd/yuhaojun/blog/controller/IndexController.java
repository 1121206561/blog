package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.dao.UserDAO;
import com.jxnd.yuhaojun.blog.dto.PaginationDTO;
import com.jxnd.yuhaojun.blog.dto.QuestionDTO;
import com.jxnd.yuhaojun.blog.model.User;
import com.jxnd.yuhaojun.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserDAO dao;
    @Autowired
    private QuestionService service;

    @GetMapping("/index")
    public String hello(HttpServletRequest request, Model model, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "5") Integer size) {
        //每次进入主页面时就会对数据库进行查询是否你已经成功登录过
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
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
        }
        PaginationDTO paginationDTO = service.select(page, size);
        model.addAttribute("paginationDTO", paginationDTO);
        return "index";
    }
}
