package com.jxnd.yuhaojun.blog.service;

import com.jxnd.yuhaojun.blog.dao.CommentDAO;
import com.jxnd.yuhaojun.blog.dao.QuestionDAO;
import com.jxnd.yuhaojun.blog.dao.UserDAO;
import com.jxnd.yuhaojun.blog.dao.notificationDAO;
import com.jxnd.yuhaojun.blog.dto.NotificationDTO;
import com.jxnd.yuhaojun.blog.model.Comment;
import com.jxnd.yuhaojun.blog.model.Notification;
import com.jxnd.yuhaojun.blog.model.Question;
import com.jxnd.yuhaojun.blog.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NotificationService {
    @Autowired
    private notificationDAO notificationDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private CommentDAO commentDAO;

    public Set<NotificationDTO> selectByUser(String user) {
        List<Notification> notificationList = notificationDAO.selectByUser(user);

        Set<NotificationDTO> notificationDTOSet = new HashSet<>();
        for (Notification notification : notificationList) {
            if (!notification.getNotifier().equals(notification.getReceiver())) {
                NotificationDTO notificationDTO = new NotificationDTO();
                BeanUtils.copyProperties(notification, notificationDTO);
                User user1 = userDAO.selectByCreator(notification.getNotifier());
                notificationDTO.setUser(user1);
                if (notification.getType() == 1) {
                    Question question = questionDAO.selectById(Integer.valueOf(notification.getOuterid().toString()));
                    notificationDTO.setQuestion(question);
                } else {
                    Comment comment = commentDAO.select(notification.getOuterid());
                    notificationDTO.setComment(comment);
                }
                notificationDTOSet.add(notificationDTO);
            }
        }
        return notificationDTOSet;
    }

    public Long selectByStatus(String Login) {
        return notificationDAO.selectByStatus(Login);
    }
}
