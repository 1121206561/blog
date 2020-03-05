package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.dao.CommentDAO;
import com.jxnd.yuhaojun.blog.dao.notificationDAO;
import com.jxnd.yuhaojun.blog.enums.NotificationEnum;
import com.jxnd.yuhaojun.blog.exception.CustomizeErrorCode;
import com.jxnd.yuhaojun.blog.exception.CustomizeException;
import com.jxnd.yuhaojun.blog.model.Comment;
import com.jxnd.yuhaojun.blog.model.Notification;
import com.jxnd.yuhaojun.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private notificationDAO notificationDAO;
    @Autowired
    private CommentDAO commentDAO;

    @GetMapping("/notification/{id}")
    public String edit(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        Notification notification = notificationDAO.selectById(id);
        User user = (User) request.getSession().getAttribute("user");
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NO_COMMENT_DELETE);
        }
        if (notification.getType() == NotificationEnum.REPLAY_QUESSTION.getType()) {
            if (!notification.getReceiver().equals(user.getLogin())) {
                throw new CustomizeException(CustomizeErrorCode.NOT_YOUR_QUESTION);
            }
            notificationDAO.updateByStatus(notification);
            return "redirect:/MyQuestion/" + notification.getOuterid();
        } else {
            Comment commentOwn = commentDAO.select(notification.getOuterid());
            notificationDAO.updateByStatus(notification);
            return "redirect:/MyQuestion/" + commentOwn.getParentId();
        }
    }
}
