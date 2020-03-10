package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.dao.AdDAO;
import com.jxnd.yuhaojun.blog.model.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdService {
    @Autowired
    private AdDAO adDAO;

    public List<Ad> selectAll() {
        return adDAO.selectAll();
    }
}
