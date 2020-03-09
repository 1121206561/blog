package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.dao.UserDAO;
import com.jxnd.yuhaojun.blog.dao.VideoCommentDAO;
import com.jxnd.yuhaojun.blog.dto.VideosCommentDTO;
import com.jxnd.yuhaojun.blog.dto.videoCommentDTO;
import com.jxnd.yuhaojun.blog.enums.VideoCommentTypeEnum;
import com.jxnd.yuhaojun.blog.exception.CustomizeErrorCode;
import com.jxnd.yuhaojun.blog.exception.CustomizeException;
import com.jxnd.yuhaojun.blog.model.User;
import com.jxnd.yuhaojun.blog.model.VideoComment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoCommentService {
    @Autowired
    private VideoCommentDAO videoCommentDAO;
    @Autowired
    private UserDAO userDAO;

    public List<VideosCommentDTO> selectALL(Long aid) {
        List<VideoComment> videoCommentList = videoCommentDAO.selectALL(aid);
        List<VideosCommentDTO> videosCommentDTOS = new ArrayList<>();
        for (VideoComment videoComment : videoCommentList) {
            VideosCommentDTO videosCommentDTO = new VideosCommentDTO();
            BeanUtils.copyProperties(videoComment, videosCommentDTO);
            User user = userDAO.selectByCreator(videoComment.getCommentator());
            videosCommentDTO.setUser(user);
            videosCommentDTOS.add(videosCommentDTO);
        }
        return videosCommentDTOS;
    }

    public void insert(videoCommentDTO videoCommentDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        VideoComment videoComment = new VideoComment();
        videoComment.setCommentator(user.getLogin());
        videoComment.setContent(videoCommentDTO.getContent());
        videoComment.setGmtCreate(System.currentTimeMillis());
        videoComment.setGmtModified(videoComment.getGmtCreate());
        videoComment.setParentAid(videoCommentDTO.getParent_aid());
        videoComment.setLikeCount(0);
        if (videoCommentDTO.getType() == 1) {
            videoComment.setType(VideoCommentTypeEnum.Comment.getType());
        } else {
            videoComment.setType(VideoCommentTypeEnum.Barrage.getType());
        }
        videoCommentDAO.insert(videoComment);
    }
}
