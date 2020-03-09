package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.dao.VideosDAO;
import com.jxnd.yuhaojun.blog.model.Videos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.UUID;

@Controller
public class uploadVideoController {
    @Autowired
    private VideosDAO videosDAO;

    @GetMapping("/uploadVideo")
    public String uploadVideo(Long roomNumber, Integer totalCount) throws Exception {
        Videos videos = new Videos();
        videos.setGmtCreate(System.currentTimeMillis());
        videos.setGmtModified(videos.getGmtCreate());
        videos.setAid(roomNumber);
        videos.setPagecount(totalCount);
        videos.setImg("https://random.52ecy.cn/randbg.php");
        videosDAO.insert(videos);
        return "redirect:/videos";
    }
}
