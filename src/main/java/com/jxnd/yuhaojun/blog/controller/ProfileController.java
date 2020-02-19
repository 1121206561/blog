package com.jxnd.yuhaojun.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action, Model model) {
        if ("questions".equals(action)) {
            model.addAttribute("sectionName", "我的问题");
            model.addAttribute("section", "questions");
        } else if ("messages".equals(action)) {
            model.addAttribute("sectionName", "消息中心");
            model.addAttribute("section", "messages");
        } else if ("comments".equals(action)) {
            model.addAttribute("sectionName", " 最新回复");
            model.addAttribute("section", "comments");
        }
        return "profile";
    }
}
