package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.dao.VideosDAO;
import com.jxnd.yuhaojun.blog.dto.videoDTO;
import com.jxnd.yuhaojun.blog.model.Videos;
import com.jxnd.yuhaojun.blog.provider.QcloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class uploadVideoController {
    @Autowired
    private VideosDAO videosDAO;
    @Autowired
    private QcloudProvider qcloudProvider;

    @RequestMapping("/uploadVideo")
    public String uploadVideo(videoDTO videoDTO) throws IOException {
        MultipartFile file = videoDTO.getImg_file();
        String url = qcloudProvider.FileToCos(file.getInputStream());
        Videos videos = new Videos();
        videos.setImg(url);
        videos.setPagecount(videoDTO.getTotalCount());
        videos.setAid(videoDTO.getRoomNumber());
        videos.setGmtCreate(System.currentTimeMillis());
        videos.setGmtModified(videos.getGmtCreate());
        videosDAO.insert(videos);
        return "redirect:/videos";
    }
}
