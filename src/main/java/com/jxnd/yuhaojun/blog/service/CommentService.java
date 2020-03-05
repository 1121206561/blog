package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.Mapper.UserMapper;
import com.jxnd.yuhaojun.blog.dao.CommentDAO;
import com.jxnd.yuhaojun.blog.dao.QuestionDAO;
import com.jxnd.yuhaojun.blog.dao.notificationDAO;
import com.jxnd.yuhaojun.blog.dto.CommentDisDTO;
import com.jxnd.yuhaojun.blog.enums.CommentTypeEnum;
import com.jxnd.yuhaojun.blog.enums.NotificationEnum;
import com.jxnd.yuhaojun.blog.enums.NotificationStatusEnum;
import com.jxnd.yuhaojun.blog.exception.CustomizeErrorCode;
import com.jxnd.yuhaojun.blog.exception.CustomizeException;
import com.jxnd.yuhaojun.blog.model.Comment;
import com.jxnd.yuhaojun.blog.model.Notification;
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
    @Autowired
    private notificationDAO notificationDAO;

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
            //增加通知
            Notification notification = notice(comment, question.getCreator(), NotificationEnum.REPLAY_QUESSTION);
            notificationDAO.insertNotification(notification);

            //直接评论的回复
        } else {
            Comment comment1 = commentDAO.select(comment.getParentId());
            if (comment1 == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentDAO.insert(comment);
            //每次对评论的回复,会让点赞数+1'
            commentDAO.updateLikeCount(comment.getParentId());
            //增加通知
            Notification notification = notice(comment, comment1.getCommentator(), NotificationEnum.REPLAU_COMMENT);
            notificationDAO.insertNotification(notification);
        }
        questionDAO.updateByComment(Integer.valueOf(comment.getParentId().toString()));
    }

    public Notification notice(Comment comment, String gmtCreator, NotificationEnum notificationEnum) {
        Notification notification = new Notification();
        notification.setNotifier(comment.getCommentator());
        notification.setOuterid(comment.getParentId());
        notification.setReceiver(gmtCreator);
        if (notification.getNotifier().equals(notification.getReceiver())) {
            notification.setStatus(NotificationStatusEnum.READ.getStatus());
        } else {
            notification.setStatus(NotificationStatusEnum.NOT_READ.getStatus());
        }
        notification.setType(notificationEnum.getType());
        notification.setGmtCreate(System.currentTimeMillis());
        return notification;
    }

    public List<CommentDisDTO> selectByComment(Integer id, CommentTypeEnum commentTypeEnum) {
        //跟据评论的评论人获取它的信息
        //获取评论信息的去掉重复后的评论人
        List<Comment> comments = commentDAO.selectByComment(id, commentTypeEnum);
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        Set<String> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<User> users = new ArrayList<>();
        for (String commentator : commentators) {
            users.add(userMapper.selectByCreator(commentator));
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
