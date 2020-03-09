package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.dao.VideosDAO;
import com.jxnd.yuhaojun.blog.dto.VideosCommentDTO;
import com.jxnd.yuhaojun.blog.dto.videoCommentDTO;
import com.jxnd.yuhaojun.blog.model.VideoComment;
import com.jxnd.yuhaojun.blog.model.Videos;
import com.jxnd.yuhaojun.blog.service.VideoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class VideosController {
    @Autowired
    private VideosDAO videosDAO;
    @Autowired
    private VideoCommentService videoCommentService;

    @GetMapping("videos")
    public String ToVideos(Model model) {
        List<Videos> videosList = videosDAO.selectALL();
        model.addAttribute("videosList", videosList);
        return "videos";
    }

    @RequestMapping("/video")
    public String ToVideo(@RequestParam(name = "page", defaultValue = "1") String page, Long aid, Model model) {
        List<VideosCommentDTO> videosCommentDTOList = videoCommentService.selectALL(aid);
        model.addAttribute("videoCommentList", videosCommentDTOList);
        Integer PageCount = videosDAO.selectByAid(aid).getPagecount();
        model.addAttribute("PageCount", PageCount);
        model.addAttribute("page", page);
        model.addAttribute("aid", aid);
        return "video";
    }
}
