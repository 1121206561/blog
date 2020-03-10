package com.jxnd.yuhaojun.blog.dao.impl;

import com.jxnd.yuhaojun.blog.Mapper.AdMapper;
import com.jxnd.yuhaojun.blog.dao.AdDAO;
import com.jxnd.yuhaojun.blog.model.Ad;
import com.jxnd.yuhaojun.blog.model.AdExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdDAOImpl implements AdDAO {
    @Autowired
    private AdMapper adMapper;

    @Override
    public List<Ad> selectAll() {
        return adMapper.selectByExample(new AdExample());
    }
}
