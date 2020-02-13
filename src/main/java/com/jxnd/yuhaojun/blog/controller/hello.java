package com.jxnd.yuhaojun.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class hello {
    @GetMapping("/hello")
    public String hello(String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
}
