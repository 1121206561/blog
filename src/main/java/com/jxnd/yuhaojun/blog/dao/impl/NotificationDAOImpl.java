package com.jxnd.yuhaojun.blog.dao.impl;

import com.jxnd.yuhaojun.blog.Mapper.NotificationMapper;
import com.jxnd.yuhaojun.blog.dao.notificationDAO;
import com.jxnd.yuhaojun.blog.enums.NotificationStatusEnum;
import com.jxnd.yuhaojun.blog.model.Notification;
import com.jxnd.yuhaojun.blog.model.NotificationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationDAOImpl implements notificationDAO {
    @Autowired
    private NotificationMapper notificationMapper;


    @Override
    public void insertNotification(Notification notification) {
        notificationMapper.insert(notification);
    }

    @Override
    public List<Notification> selectByUser(String user) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(user);
        notificationExample.setOrderByClause("gmt_create desc");
        List<Notification> notificationList = notificationMapper.selectByExample(notificationExample);
        return notificationList;
    }

    @Override
    public Long selectByStatus(String login) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andStatusEqualTo(1).andReceiverEqualTo(login);
        return notificationMapper.countByExample(notificationExample);
    }

    @Override
    public Notification selectById(Long id) {
        return notificationMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByStatus(Notification notification) {
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);
    }
}
