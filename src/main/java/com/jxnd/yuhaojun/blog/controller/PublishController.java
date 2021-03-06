package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.coach.TagsCoach;
import com.jxnd.yuhaojun.blog.dao.QuestionDAO;
import com.jxnd.yuhaojun.blog.dto.TagsDTO;
import com.jxnd.yuhaojun.blog.exception.CustomizeErrorCode;
import com.jxnd.yuhaojun.blog.exception.CustomizeException;
import com.jxnd.yuhaojun.blog.model.Question;
import com.jxnd.yuhaojun.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PublishController {
    @Autowired
    private QuestionDAO dao;

    @GetMapping("publish")
    public String publish(Model model) {
        List<TagsDTO> tagsDTOList = TagsCoach.showTags();
        model.addAttribute("tagsDTOList", tagsDTOList);
        return "publish";
    }

    //对用户发布的信息进行处理
    @PostMapping("publish")
    public String doPublish(Integer id, String title, String description, String tag, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        List<TagsDTO> tagsDTOList = TagsCoach.showTags();
        model.addAttribute("tagsDTOList", tagsDTOList);
        model.addAttribute("id", id);
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "内容不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        boolean b = TagsCoach.isTags(tag);
        if (!b) {
            model.addAttribute("error", "标签不符合规范,请重新输入");
            return "publish";
        }

        Question question = new Question();
        question.setCreator(user.getLogin());
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        if (id != null) {
            question.setId(id);
            dao.update(question);
        } else {
            dao.insert(question);
        }
        return "redirect:/index";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id, Model model) {
        Question question = dao.selectById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        List<TagsDTO> tagsDTOList = TagsCoach.showTags();
        model.addAttribute("tagsDTOList", tagsDTOList);
        return "publish";
    }
}
