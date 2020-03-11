package com.jxnd.yuhaojun.blog.dao.impl;

import com.jxnd.yuhaojun.blog.Mapper.ActiveMapper;
import com.jxnd.yuhaojun.blog.dao.ActiveDAO;
import com.jxnd.yuhaojun.blog.model.Active;
import com.jxnd.yuhaojun.blog.model.ActiveExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActiveDAOImpl implements ActiveDAO {
    @Autowired
    private ActiveMapper activeMapper;

    @Override
    public List<Active> selectAll() {
        return activeMapper.selectByExample(new ActiveExample());
    }
}
