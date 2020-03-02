package com.jxnd.yuhaojun.blog.dto;

import com.jxnd.yuhaojun.blog.model.Comment;
import com.jxnd.yuhaojun.blog.model.Question;
import com.jxnd.yuhaojun.blog.model.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private String notifier;
    private Long outerid;
    private String receiver;
    private Integer status;
    private Integer type;
    private Long gmtCreate;
    private User user;
    private Question question;
    private Comment comment;
}
