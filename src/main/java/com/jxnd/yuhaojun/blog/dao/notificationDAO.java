package com.jxnd.yuhaojun.blog.dao;

import com.jxnd.yuhaojun.blog.model.Notification;

import java.util.List;

public interface notificationDAO {
    void insertNotification(Notification notification);

    List<Notification> selectByUser(String user);

    Long selectByStatus(String Login);

    Notification selectById(Long id);

    void updateByStatus(Notification notification);
}
