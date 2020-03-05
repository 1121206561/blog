package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.dto.CommentDTO;
import com.jxnd.yuhaojun.blog.dto.CommentDisDTO;
import com.jxnd.yuhaojun.blog.dto.ResultDTO;
import com.jxnd.yuhaojun.blog.enums.CommentTypeEnum;
import com.jxnd.yuhaojun.blog.exception.CustomizeErrorCode;
import com.jxnd.yuhaojun.blog.model.Comment;
import com.jxnd.yuhaojun.blog.model.User;
import com.jxnd.yuhaojun.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "comment", method = RequestMethod.POST)
    @ResponseBody
    public Object comment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if (commentDTO == null || commentDTO.getContent() == null || "".trim().equals(commentDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_CONTENT);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParent_id());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreator(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreator());
        comment.setLikeCount(0L);
        comment.setCommentator(user.getLogin());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    //点击评论显示按钮显示回复的评论
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object comments(@PathVariable(name = "id") Long id) {
        List<CommentDisDTO> commentDisDTOS = commentService.selectByComment(Integer.valueOf(id.toString()), CommentTypeEnum.Comment);
        return ResultDTO.okOf(commentDisDTOS);
    }
}
