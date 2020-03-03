package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.dao.ProfileDAO;
import com.jxnd.yuhaojun.blog.dao.UserDAO;
import com.jxnd.yuhaojun.blog.dto.PaginationDTO;
import com.jxnd.yuhaojun.blog.dto.QuestionDTO;
import com.jxnd.yuhaojun.blog.model.Question;
import com.jxnd.yuhaojun.blog.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileDAO profileDAO;
    @Autowired
    private UserDAO userDAO;

    public PaginationDTO select(Integer page, Integer size, String creator) {
        Integer count = profileDAO.selectByCountCreator(creator);
        int totalCount;
        //计算总页数
        if (count % size == 0) {
            totalCount = count / size;
        } else {
            totalCount = count / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalCount) {
            page = totalCount;
        }
        Integer offset = (page - 1) * size;
        //查询出文章信息
        List<Question> list = profileDAO.select(offset, size, creator);
        //文章信息和用户信息包装起来
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        //查询出来的信息和分页信息包装起来
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : list) {
            User user = userDAO.selectByCreator(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOList);
        paginationDTO.setpagination(totalCount, page);
        return paginationDTO;
    }
}
