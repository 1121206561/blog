package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.dao.QuestionDAO;
import com.jxnd.yuhaojun.blog.dao.UserDAO;
import com.jxnd.yuhaojun.blog.dto.PaginationDTO;
import com.jxnd.yuhaojun.blog.dto.QuestionDTO;
import com.jxnd.yuhaojun.blog.model.Question;
import com.jxnd.yuhaojun.blog.model.User;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//查询出来所有的发布的文章的信息并传递给首页
@Service
public class QuestionService {
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private UserDAO userDAO;

    public PaginationDTO select(String HotTag, String seach, Integer page, Integer size) {
        Integer count;
        if (seach == null || seach.trim().equals("")) {
            if (HotTag == null || HotTag.trim().equals("")) {
                count = questionDAO.selectByCount();
            } else {
                count = questionDAO.selectByCountTag(HotTag);
            }
        } else {
            if (HotTag == null || HotTag.trim().equals("")) {
                count = questionDAO.selectByCountSeach(seach);
            } else {
                count = questionDAO.selectByCountSeachTag(seach, HotTag);
            }
        }
        int totalCount;
        //计算总页数
        if (count % size == 0) {
            totalCount = count / size;
        } else {
            totalCount = count / size + 1;
        }
        if (page > totalCount) {
            page = totalCount;
        }
        if (page < 1) {
            page = 1;
        }
        Integer offset = (page - 1) * size;
        List<Question> list;
        //查询出文章信息
        if (seach == null || seach.trim().equals("")) {
            if (HotTag == null || HotTag.trim().equals("")) {
                list = questionDAO.select(offset, size);
            } else {
                list = questionDAO.selectByLimitTag(offset, size, HotTag);
            }
        } else {
            if (HotTag == null || HotTag.trim().equals("")) {
                list = questionDAO.selectBySeach(seach, offset, size);
            } else {
                list = questionDAO.selectByLimitSeachTag(offset, size, HotTag, seach);
            }
        }
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
                                     