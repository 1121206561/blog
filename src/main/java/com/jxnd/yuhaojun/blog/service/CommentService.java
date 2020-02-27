package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.Mapper.UserMapper;
import com.jxnd.yuhaojun.blog.dao.CommentDAO;
import com.jxnd.yuhaojun.blog.dao.QuestionDAO;
import com.jxnd.yuhaojun.blog.dto.CommentDisDTO;
import com.jxnd.yuhaojun.blog.enums.CommentTypeEnum;
import com.jxnd.yuhaojun.blog.exception.CustomizeErrorCode;
import com.jxnd.yuhaojun.blog.exception.CustomizeException;
import com.jxnd.yuhaojun.blog.model.Comment;
import com.jxnd.yuhaojun.blog.model.Question;
import com.jxnd.yuhaojun.blog.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private UserMapper userMapper;

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

    public List<CommentDisDTO> selectByComment(Integer id,CommentTypeEnum commentTypeEnum) {
        //跟据评论的评论人获取它的信息
        //获取评论信息的去掉重复后的评论人
        List<Comment> comments = commentDAO.selectByComment(id,commentTypeEnum);
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<User> users = new ArrayList<>();
        for (Integer commentator : commentators) {
            users.add(userMapper.selectByCreator(commentator.toString()));
        }
        List<CommentDisDTO> commentDisDTOList = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDisDTO commentDisDTO = new CommentDisDTO();
            BeanUtils.copyProperties(comment, commentDisDTO);
            for (User user1 : users) {
                if (comment.getCommentator().equals(user1.getLogin())) {
                    commentDisDTO.setUser(user1);
                }
            }
            commentDisDTOList.add(commentDisDTO);
        }
        return commentDisDTOList;
    }
}
