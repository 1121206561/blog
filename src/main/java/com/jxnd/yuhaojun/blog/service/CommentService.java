package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.dao.CommentDAO;
import com.jxnd.yuhaojun.blog.dao.QuestionDAO;
import com.jxnd.yuhaojun.blog.dto.CommentDisDTO;
import com.jxnd.yuhaojun.blog.enums.CommentTypeEnum;
import com.jxnd.yuhaojun.blog.exception.CustomizeErrorCode;
import com.jxnd.yuhaojun.blog.exception.CustomizeException;
import com.jxnd.yuhaojun.blog.model.Comment;
import com.jxnd.yuhaojun.blog.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private QuestionDAO questionDAO;

    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExit(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_NOT_FOUND);
        }
        //直接评论的问题
        if (comment.getType() == CommentTypeEnum.Question.getType()) {
            Question question = questionDAO.selectById(Integer.valueOf(comment.getParentId().toString()));
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentDAO.insert(comment);
            //直接评论的回复
        } else {
            Comment comment1 = commentDAO.select(comment.getParentId());
            if (comment1 == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentDAO.insert(comment);
        }
        questionDAO.updateByComment(Integer.valueOf(comment.getParentId().toString()));
    }

    public List<Comment> selectByComment(Integer id) {
        List<Comment> list = commentDAO.selectByComment(id);
        if (list.size() == 0) {
            return new ArrayList<>();
        }
        return list;
    }
}
