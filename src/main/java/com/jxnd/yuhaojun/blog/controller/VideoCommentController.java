package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.dto.ResultDTO;
import com.jxnd.yuhaojun.blog.dto.videoCommentDTO;
import com.jxnd.yuhaojun.blog.service.VideoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class VideoCommentController {
    @Autowired
    private VideoCommentService videoCommentService;

    @RequestMapping(value = "videoComment", method = RequestMethod.POST)
    @ResponseBody
    public Object videoComment(@RequestBody videoCommentDTO videoCommentDTO, HttpServletRequest request) {
        videoCommentService.insert(videoCommentDTO,request);
        return ResultDTO.okOf();
    }
}
