package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.Mapper.PersonMapper;
import com.jxnd.yuhaojun.blog.dao.PersonDAO;
import com.jxnd.yuhaojun.blog.exception.CustomizeErrorCode;
import com.jxnd.yuhaojun.blog.exception.CustomizeException;
import com.jxnd.yuhaojun.blog.model.Person;
import com.jxnd.yuhaojun.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonDAO personDAO;

    public List<Person> selectAll(String name) {
        return personDAO.selectAll(name);
    }

    public void insert(Person person, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        person.setImg(user.getAvatar_url());
        person.setName(user.getLogin());
        personDAO.insert(person);
    }

    public Person selectById(Long id) {
        return personDAO.selectById(id);
    }

    public void update(Person person, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        person.setImg(user.getAvatar_url());
        person.setName(user.getLogin());
        personDAO.update(person);
    }
}
