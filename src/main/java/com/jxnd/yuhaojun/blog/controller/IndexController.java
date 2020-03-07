package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.coach.HotTagsTasksCoach;
import com.jxnd.yuhaojun.blog.dto.PaginationDTO;
import com.jxnd.yuhaojun.blog.schedul.HotTagTasks;
import com.jxnd.yuhaojun.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private HotTagsTasksCoach hotTagsTasksCoach;

    @GetMapping("/index")
    public String hello(String HotTag, String seach, Model model, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PaginationDTO paginationDTO = questionService.select(HotTag, seach, page, size);
        model.addAttribute("paginationDTO", paginationDTO);
        model.addAttribute("seach", seach);
        List<Map.Entry<String, Integer>> HotTaglist = hotTagsTasksCoach.getTagList();
        model.addAttribute("HotTaglist", HotTaglist);
        model.addAttribute("tag", HotTag);
        return "index";
    }
}
