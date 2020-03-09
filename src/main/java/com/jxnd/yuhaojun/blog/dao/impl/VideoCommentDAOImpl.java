package com.jxnd.yuhaojun.blog.dao.impl;

import com.jxnd.yuhaojun.blog.Mapper.VideoCommentMapper;
import com.jxnd.yuhaojun.blog.dao.VideoCommentDAO;
import com.jxnd.yuhaojun.blog.model.VideoComment;
import com.jxnd.yuhaojun.blog.model.VideoCommentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoCommentDAOImpl implements VideoCommentDAO {
    @Autowired
    private VideoCommentMapper videoCommentMapper;

    @Override
    public List<VideoComment> selectALL(Long aid) {
        VideoCommentExample videoCommentExample = new VideoCommentExample();
        videoCommentExample.createCriteria()
                .andParentAidEqualTo(aid);
        List<VideoComment> videoCommentList = videoCommentMapper.selectByExample(videoCommentExample);
        return videoCommentList;
    }

    @Override
    public void insert(VideoComment videoComment) {
        videoCommentMapper.insert(videoComment);
    }
}
