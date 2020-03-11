package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.dao.ActiveDAO;
import com.jxnd.yuhaojun.blog.model.Active;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveService {
    @Autowired
    private ActiveDAO activeDAO;
    public List<Active> selectAll() {
        return activeDAO.selectAll();
    }
}
