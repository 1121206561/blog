package com.jxnd.yuhaojun.blog.dao;

import com.jxnd.yuhaojun.blog.model.Videos;

import java.util.List;

public interface VideosDAO {
    List<Videos> selectALL();

    Videos selectByAid(Long aid);

    void insert(Videos videos);
}
