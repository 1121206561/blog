package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.Mapper.UserMapper;
import com.jxnd.yuhaojun.blog.dto.CommentDisDTO;
import com.jxnd.yuhaojun.blog.dto.QuestionDTO;
import com.jxnd.yuhaojun.blog.enums.CommentTypeEnum;
import com.jxnd.yuhaojun.blog.exception.CustomizeErrorCode;
import com.jxnd.yuhaojun.blog.exception.CustomizeException;
import com.jxnd.yuhaojun.blog.model.Question;
import com.jxnd.yuhaojun.blog.model.User;
import com.jxnd.yuhaojun.blog.service.CommentService;
import com.jxnd.yuhaojun.blog.service.MyQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private MyQuestionService myQuestionService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentService commentService;

    @GetMapping("/MyQuestion/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model) {
        //更新阅读数
        myQuestionService.updateByView(id);
        Question question = myQuestionService.selectById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByCreator(question.getCreator());
        questionDTO.setUser(user);
        model.addAttribute("questionDTO", questionDTO);
        //查询所有评论
        List<CommentDisDTO> commentDisDTOS = commentService.selectByComment(id, CommentTypeEnum.Question);
        model.addAttribute("commentDisDTOList", commentDisDTOS);
        return "myQuestion";
    }

}
