package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.dto.PaginationDTO;
import com.jxnd.yuhaojun.blog.model.User;
import com.jxnd.yuhaojun.blog.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request, @PathVariable(name = "action") String action, Model model, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "5") Integer size) {
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
        User user = (User) request.getSession().getAttribute("user");
        String creator = user.getLogin();
        PaginationDTO paginationDTO = profileService.select(page, size, creator);
        model.addAttribute("paginationDTO", paginationDTO);
        return "profile";
    }
}
