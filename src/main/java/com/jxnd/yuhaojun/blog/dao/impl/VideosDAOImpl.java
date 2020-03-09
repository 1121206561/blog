package com.jxnd.yuhaojun.blog.dao.impl;

import com.jxnd.yuhaojun.blog.Mapper.VideosMapper;
import com.jxnd.yuhaojun.blog.dao.VideosDAO;
import com.jxnd.yuhaojun.blog.model.Videos;
import com.jxnd.yuhaojun.blog.model.VideosExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideosDAOImpl implements VideosDAO {
    @Autowired
    private VideosMapper videosMapper;

    @Override
    public List<Videos> selectALL() {
        return videosMapper.selectByExample(new VideosExample());
    }

    @Override
    public Videos selectByAid(Long aid) {
        VideosExample videosExample = new VideosExample();
        videosExample.createCriteria().andAidEqualTo(aid);
        return videosMapper.selectByExample(videosExample).get(0);
    }

    @Override
    public void insert(Videos videos) {
        videosMapper.insert(videos);
    }
}
